package ru.javawebinar.topjava.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by lashi on 09.04.2017.
 */
public class DbCleaner extends ResourceDatabasePopulator {
    private static final ResourceLoader loader = new DefaultResourceLoader();

    private DataSource dataSource;

    public DbCleaner(DataSource dataSource, String scriptLocation) {
        super(loader.getResource(scriptLocation));
        this.dataSource = dataSource;
    }

    public void execute(){
        DatabasePopulatorUtils.execute(this, dataSource);
    }
}
