package com.kh.app.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		String path = this.getClass().getResource("/Wallet_final").getPath(); // src/main/resources 하위에 위치시킨 지갑폴더를 target/classse하위 클래스패스에서 참조한다.
		String os = System.getProperty("os.name").toLowerCase(); 
		if(os.contains("win"))
			path = path.replaceFirst("/", ""); // window에서는 시작되는 /를 제거해야 한다.
		dataSource.setURL("jdbc:oracle:thin:@final_high?TNS_ADMIN=" + path); // 서비스명은 지갑폴더명에서 WALLET_을 제외한 소문자이름_high (high/midium/low 중 선택)
		dataSource.setUser("kh");
		dataSource.setPassword("eoghWkdWKd1!");
		return dataSource;
	}
}
