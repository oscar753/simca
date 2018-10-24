package mx.org.ift.simca.arq.core.persistence;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import mx.org.ift.simca.arq.core.model.Bitacora;
import mx.org.ift.simca.arq.core.model.TipoBitacora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Prueba utilizada para verificar de manera general que mybatis está configurado.
 * 
 * Borrar y agregar pruebas que relacionadas con los servicios.
 * ;
 * @author 
 */
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"})
public class BitacoraTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private BitacoraMapper bitacoraMapper;
    @Autowired
    private TipoBitacoraMapper tipoBitacoraMapper;
    
    private Logger LOGGER = Logger.getLogger(this.getClass());
    
    @Test (enabled = false)
    public void puebaEscritura() {
        List<TipoBitacora> tiposBitacora = tipoBitacoraMapper.getAll();
        
        assert !tiposBitacora.isEmpty() : "Se esperaba al menos un tipo de bitácora";
        
        LOGGER.info("************ tipoBitacoraMapper.getAll()");
        if (tiposBitacora != null) 
        {
            for (Iterator iter = tiposBitacora.iterator(); iter.hasNext();) {
                    TipoBitacora element = (TipoBitacora) iter.next();
                    LOGGER.info("-->Tipo Bitacora:" + element.getDescripcion());
            }

            Bitacora bitacora = new Bitacora();
            bitacora.setTipoBitacoraFk(tiposBitacora.get(0).getTipoBitacoraPk());
            bitacora.setEventDate(new Date());
            bitacora.setIp("localhost");
            bitacora.setExtraInfo("Una prueba desde aca");
            LOGGER.info("************ bitacoraMapper.insert()");
            //System.out.println("************ bitacoraMapper.insert()");
            bitacoraMapper.insert(bitacora);

            assert bitacora.getBitacoraPk() != null : "No se pudo recuperar el id de la bitácora ingresada";
            LOGGER.info("************ bitacoraMapper.delete()");
            bitacoraMapper.delete(bitacora);
        }
    }

    
    @Test (enabled = false)
    public void pruebaExpresionArgumentosFormulaFIN() {
        Pattern p = null;
        Matcher m = null;
        //String formula="((:SBSueldoBase/:DiasPorMes)*:DiasLaborados)+:GTotal";
        String formula=":SBSueldoBase/:DiasPorMes";
        p = Pattern.compile("([/*+-]+)");
        m = p.matcher(formula);
        if (m.find()){
            String separadorArgumento=":";
            Integer totArgumentos=0;
            Integer posicion=formula.indexOf(separadorArgumento);
            while (posicion != -1) { //mientras se encuentre el caracter
                totArgumentos++;           //se cuenta
                posicion = formula.indexOf(separadorArgumento, posicion + 1);
            }        
            LOGGER.info("-->totArgumentos:" + totArgumentos);


            String[] argumentos=new String[totArgumentos];
            totArgumentos=0;
            Pattern pattern1 = Pattern.compile("(\\:[^#0-9/*+$%-:]+)");
            Matcher matcher1 = pattern1.matcher(formula);
            while (matcher1.find()) {
               //LOGGER.info("1---> " + matcher1.group(1));
               totArgumentos++;
               argumentos[totArgumentos-1]=matcher1.group(1);
            }

            for (int i = 0 ; i < argumentos.length ; i++) {
                LOGGER.info("Argumento en el indice " + i + ": " + argumentos[i]);
            }        
            LOGGER.info("-->Fin correcto");
        }else{    
            LOGGER.info("-->Formula sin operandos");
        }
    }

    
    @Test (enabled = false)
    public void pruebaReplaceFormula() {
        Pattern p = null;
        Matcher m = null;
        //String formula="((:SBSueldoBase/:DiasPorMes)*:DiasLaborados)+:GTotal";
        String formula="(:SBSueldo*:SBSueldoBase)";
        p = Pattern.compile("([/*+-]+)");
        m = p.matcher(formula);
        if (m.find()){
            String separadorArgumento=":";
            Integer totArgumentos=0;
            Integer posicion=formula.indexOf(separadorArgumento);
            while (posicion != -1) { //mientras se encuentre el caracter
                totArgumentos++;           //se cuenta
                posicion = formula.indexOf(separadorArgumento, posicion + 1);
            }        
            LOGGER.info("-->totArgumentos:" + totArgumentos);


            String[] argumentos=new String[totArgumentos];
            totArgumentos=0;
            Pattern pattern1 = Pattern.compile("(\\:[^#0-9/*+$%-:]+)");
            Matcher matcher1 = pattern1.matcher(formula);
            while (matcher1.find()) {
               //LOGGER.info("1---> " + matcher1.group(1));
               totArgumentos++;
               argumentos[totArgumentos-1]=matcher1.group(1);
            }
            
            String valor="2";
            formula="(:SBSueldoBase*:SBSueldo)";
            String nuevaFormula="(:SBSueldoBase*:SBSueldo)";
            String mExp;
            for (int i = 0 ; i < argumentos.length ; i++) {
                LOGGER.info("Argumento en el indice " + i + ": " + argumentos[i]);
                mExp="(\\" + argumentos[i] + "[^#0-9/*+$%-:])";
                //nuevaFormula=nuevaFormula.replaceAll(mExp, valor);
                
                
                p = Pattern.compile(mExp);
                m = p.matcher(formula);
                if (m.find()) {
                    formula = m.replaceAll(valor);
                    //formula = m.replaceFirst(valor);
                }
                
                
                
            }
            LOGGER.info("formula sustituida --> " + formula);
            LOGGER.info("-->Fin correcto");
        }else{    
            LOGGER.info("-->Formula sin operandos");
        }
        
        
    }
    
    @Test (enabled = false)
    public void pruebaExpresionArgumentosFormula() {
        Pattern p = null;
        Matcher m = null;
        String formula="((:SBSueldoBase/:DiasPorMes)*:DiasLaborados)+:GTotal";
        
        //Pattern pattern1 = Pattern.compile("(\\:[^#0-9/*+$%-:]+)*");
        Pattern pattern1 = Pattern.compile("(\\:[^#0-9/*+$%-:]+)");

        Matcher matcher1 = pattern1.matcher(formula);
        while (matcher1.find()) {
           LOGGER.info("1---> " + matcher1.group(1));
        }
    }

    @Test (enabled = false)
    public void pruebaFechas() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicial="";
        String fechaFinal="";
        String fechaPago="";
        String fechadispersion="";
        int diasAgregar=0;
        int diaSemana=0;
        int diaFecha=0;
        int diasDelMes=0;
        Calendar calendar = Calendar.getInstance();
        
        fechaInicial="01/02/2017";
        calendar.setTime(formatoFecha.parse(fechaInicial));

        diaSemana=calendar.get(Calendar.DAY_OF_WEEK); //1=domingo; 2=Lunes; 3=martes; 4=miercoles; 5=jueves; 6=viernes; 7=sabado
        diaFecha=calendar.get(Calendar.DAY_OF_MONTH); //Si la fecha es 28/01/2017, entonces esta variable es igual a 28
        diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Si la fecha es 28/01/2017, entonces esta variable es igual a 31
        
        fechaInicial=formatoFecha.format(calendar.getTime());        
        LOGGER.info(" ---------------->fechaInicial: " + fechaInicial + " diaSemana: " + diaSemana + " diaFecha: " + diaFecha + " diasDelMes: " + diasDelMes);
        
        if (diaFecha>=1 && diaFecha<=15){
            diasAgregar=14;
        }else {
            diasAgregar=diasDelMes-diaFecha;
        }
        
        
        calendar.add(Calendar.DAY_OF_YEAR, diasAgregar);      // numero de días a añadir, o restar
        fechaFinal=formatoFecha.format(calendar.getTime());
        LOGGER.info(" ---------------->fechaFinal: " + fechaFinal);
                
        
        // valida si el dia es sabado o domingo        
        diasAgregar=(diaSemana==1)?-2:(diaSemana==7)?-1:0;
        calendar.add(Calendar.DAY_OF_YEAR, diasAgregar);  // numero de días a añadir, o restar
        fechaPago=formatoFecha.format(calendar.getTime());
        LOGGER.info(" ---------------->fechaPago: " + fechaPago);
        
        diaSemana=calendar.get(Calendar.DAY_OF_WEEK); //1=domingo; 2=Lunes; 3=martes; 4=miercoles; 5=jueves; 6=viernes; 7=sabado
        diasAgregar=(diaSemana==1)?-3:(diaSemana==7)?-2:-2;
        calendar.add(Calendar.DAY_OF_YEAR, diasAgregar);  // numero de días a añadir, o restar
        diaSemana=calendar.get(Calendar.DAY_OF_WEEK); //1=domingo; 2=Lunes; 3=martes; 4=miercoles; 5=jueves; 6=viernes; 7=sabado
        diasAgregar=(diaSemana==1)?-3:(diaSemana==7)?-2:0;
        calendar.add(Calendar.DAY_OF_YEAR, diasAgregar);  
        fechadispersion=formatoFecha.format(calendar.getTime());
        LOGGER.info(" ---------------->fechadispersion: " + fechadispersion);

        
        
    }
    
    
}
