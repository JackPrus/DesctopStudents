package by.prus.univercity;

import by.prus.univercity.view.MyTableModel;
import by.prus.univercity.view.RowAddDialog;
import by.prus.univercity.view.StudentsFrame;
import net.bytebuddy.utility.nullability.NeverNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class UnivercityApplication{

	public static void main(String[] args) {

		ApplicationContext ctx = (ApplicationContext) new SpringApplicationBuilder(UnivercityApplication.class)
				.headless(false).run(args);

		JFrame.setDefaultLookAndFeelDecorated(true);
		EventQueue.invokeLater(()->{
			ctx.getBean(StudentsFrame.class);
		});
	}

	@Bean
	MyTableModel myTableModel(){
		return new MyTableModel();
	}

}
