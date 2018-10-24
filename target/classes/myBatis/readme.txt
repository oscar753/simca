

Se presentan tres alternativas para la base de datos (mysql, oracle e informix), en el archivo de applicationContext-persistence especificar el directorio correcto de los mappers.

Si usan mysql:

        <property name="mapperLocations" value="classpath:myBatis/mysql/**/*.xml" />

Si usan oracle:

        <property name="mapperLocations" value="classpath:myBatis/oracle/**/*.xml" />

Si usan informix:

        <property name="mapperLocations" value="classpath:myBatis/informix/**/*.xml" />



Una vez establecido el manejador de la base de datos, se pueden eliminar los archivos que no se requieran. Incluso se recomienda que los directorios queden bajo el directorio myBatis directamente.
