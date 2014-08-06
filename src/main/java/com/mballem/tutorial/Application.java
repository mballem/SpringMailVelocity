package com.mballem.tutorial;

import com.mballem.tutorial.domain.MailMessage;
import com.mballem.tutorial.service.MailService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Marcio Ballem on 05/08/2014.
 */
public class Application {

    private MailService mailService;

    private JFileChooser chooser;

    public Application(MailService mailService) {
        this.mailService = mailService;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringAccess.getSpringContext();

        Application app = new Application(ctx.getBean(MailService.class));

        app.sendMail();
    }

    private void sendMail() {
        Scanner scanner = new Scanner(System.in);
        MailMessage message;
        int option;
        do {
            System.out.println("1. Send mail");
            System.out.println("0. Exit");
            System.out.print("> ");
            option = Integer.parseInt(scanner.nextLine());
            if (option == 1) {
                message = new MailMessage();
                System.out.print("Sender name: ");
                message.setPersonal(scanner.nextLine());

                System.out.print("To: ");
                message.setTo(scanner.nextLine());

                System.out.print("Subject: ");
                message.setSubject(scanner.nextLine());

                System.out.print("Message: ");
                message.setText(scanner.nextLine());

                System.out.print("Attachments (Y or N): ");
                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    String[] attachments = addAttachemts();
                    message.addAttachment(attachments);
                }

                mailService.sendMail(message);
            }
        } while (option != 0);
    }

    private String[] addAttachemts() {
        chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        String[] paths = new String[chooser.getSelectedFiles().length];

        int retorno = chooser.showSaveDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            paths = chooser.getSelectedFile().getAbsolutePath().split(",");
        }
        return paths;
    }
}
