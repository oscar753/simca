package mx.org.ift.simca.arq.core.service.security.passUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import edu.vt.middleware.crypt.util.Base64Converter;
import edu.vt.middleware.dictionary.ArrayWordList;
import edu.vt.middleware.dictionary.WordListDictionary;
import edu.vt.middleware.dictionary.WordLists;
import edu.vt.middleware.dictionary.sort.ArraysSort;
import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DictionarySubstringRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.HistoryRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.RepeatCharacterRegexRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.WhitespaceRule;
import java.io.InputStreamReader;
import java.io.Reader;
import mx.org.ift.simca.arq.core.support.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La clase PasswordStrengthServiceImpl.
 *
 * Para mayor referencia, visitar:
 * http://code.google.com/p/vt-middleware/wiki/vtpassword
 */
public class PasswordStrengthServiceImpl extends PasswordStrengthService {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Logger para los mensajes del sistema.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordStrengthServiceImpl.class);
    /**
     * Obtiene el valor de passwordValidator.messageResolver del archivo de
     * configuración y lo asigna
     */
    @Value("${passwordValidator.messageResolver}")
    private String bundle;

    @Override
    public boolean customPasswordStrengthStrategy(String data) throws PasswordValidationException {
        return customPasswordStrengthStrategy01(data);
    }

    /**
     * Realiza la validación del password de forma simple.
     *
     * @param data el data
     * @return verdadero en caso de que el password sea correcto
     * @throws PasswordValidationException Lanza un excepción si el password no
     * cumple con el valor dado, la excepción contiene un lista con los errores
     * cometidos al generar el password
     */
    public boolean customPasswordStrengthStrategy01(String data) throws PasswordValidationException {
        // password must be between 8 and 16 chars long
        LengthRule lengthRule = new LengthRule(8, 16);

        // don't allow whitespace
        WhitespaceRule whitespaceRule = new WhitespaceRule();

        // control allowed characters
        CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
        // require at least 1 digit in passwords
        charRule.getRules().add(new DigitCharacterRule(1));
        // require at least 1 non-alphanumeric char
        charRule.getRules().add(new NonAlphanumericCharacterRule(1));
        // require at least 1 upper case char
        charRule.getRules().add(new UppercaseCharacterRule(1));
        // require at least 1 lower case char
        charRule.getRules().add(new LowercaseCharacterRule(1));
        // require at least 3 of the previous rules be met
        charRule.setNumberOfCharacteristics(3);

        // don't allow alphabetical sequences
        AlphabeticalSequenceRule alphaSeqRule = new AlphabeticalSequenceRule();

        // don't allow numerical sequences of length 3
        NumericalSequenceRule numSeqRule = new NumericalSequenceRule(3, false);

        // don't allow qwerty sequences
        QwertySequenceRule qwertySeqRule = new QwertySequenceRule();

        // don't allow 4 repeat characters
        RepeatCharacterRegexRule repeatRule = new RepeatCharacterRegexRule(4);

        // group all rules together in a List
        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(lengthRule);
        ruleList.add(whitespaceRule);
        ruleList.add(charRule);
        ruleList.add(alphaSeqRule);
        ruleList.add(numSeqRule);
        ruleList.add(qwertySeqRule);
        ruleList.add(repeatRule);

        PasswordValidator validator = getInternationalPasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData(new Password(data));
        RuleResult result = validator.validate(passwordData);
        boolean isValid = result.isValid();
        if (!isValid) {
            List<String> passwordValidationErrorList = validator.getMessages(result);
            throw new PasswordValidationException(passwordValidationErrorList);
        }
        return true;
    }

    /**
     * Realiza la validación agregando un diccionario como base.
     *
     * @param data el data
     * @param fullPathToDictionary Ruta al diccionario
     * @return verdadero en caso de que el password sea correcto
     * @throws PasswordValidationException Lanza un excepción si el password no
     * cumple con el valor dado, la excepción contiene un lista con los errores
     * cometidos al generar el password
     * @throws IOException En caso de error en la lectura del archivo
     * diccionario
     */
    public boolean customPasswordStrengthStrategy02(String data, String fullPathToDictionary) throws PasswordValidationException, IOException {
        ArrayWordList awl;
        InputStreamReader streamReader = null;
        
        try {
            streamReader = FileUtils.getInputStream(fullPathToDictionary);
            // create a case sensitive word list and sort it
            awl = WordLists.createFromReader(
                    new Reader[]{streamReader},
                    true,
                    new ArraysSort());
        } catch (Exception ex) {
            LOGGER.error("Error al crear la lista de palabras para el diccionario", ex);
            awl = null;
        } finally {
            FileUtils.close(streamReader);
        }
        
        if (awl == null) {
            LOGGER.warn("La lista de palabras era nula, no se hace la validación");
            return true;
        } else {
            // create a dictionary for searching
            WordListDictionary dict = new WordListDictionary(awl);

            DictionarySubstringRule dictRule = new DictionarySubstringRule(dict);
            dictRule.setWordLength(4); // size of words to check in the password
            dictRule.setMatchBackwards(true); // match dictionary words backwards

            List<Rule> ruleList = new ArrayList<Rule>();
            ruleList.add(dictRule);

            PasswordValidator validator = getInternationalPasswordValidator(ruleList);
            PasswordData passwordData = new PasswordData(new Password(data));
            RuleResult result = validator.validate(passwordData);
            boolean isValid = result.isValid();

            if (!isValid) {
                List<String> passwordValidationErrorList = validator.getMessages(result);
                throw new PasswordValidationException(passwordValidationErrorList);
            }
            return true;
        }
    }

    /**
     * Realiza la validación verificando que el password actual no sea igual que
     * los anteriores usados.
     *
     * @param data el data
     * @return verdadero en caso de que el password sea correcto
     * @throws PasswordValidationException Lanza un excepción si el password no
     * cumple con el valor dado, la excepción contiene un lista con los errores
     * cometidos al generar el password
     */
    public boolean customPasswordStrengthStrategy03(String data) throws PasswordValidationException {
        // base64 encoded, SHA-1 passwords
        String[] history = new String[]{
            "MwRLPWHiwj49VmNSmTsSBeFECqk=",
            "EqCiqolu0z8+T+5COOSO/+XfTCA=",
            "V0DGHz3umagyKKAbbFEbpfByzsQ=",};

        HistoryRule historyRule = new HistoryRule();
        historyRule.setDigest("SHA-1", new Base64Converter());

        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(historyRule);

        PasswordValidator validator = getInternationalPasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData(new Password(data));
        passwordData.setPasswordHistory(Arrays.asList(history));

        RuleResult result = validator.validate(passwordData);
        boolean isValid = result.isValid();

        if (!isValid) {
            List<String> passwordValidationErrorList = validator.getMessages(result);
            throw new PasswordValidationException(passwordValidationErrorList);
        }
        return true;
    }

    /**
     * Recupera el international password validator.
     *
     * @param ruleList el rule list
     * @return El international password validator
     */
    public PasswordValidator getInternationalPasswordValidator(List<Rule> ruleList) {
        return getInternationalPasswordValidator(this.bundle, ruleList);
    }
}
