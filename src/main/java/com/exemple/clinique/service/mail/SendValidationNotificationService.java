package com.exemple.clinique.service.mail;

import com.exemple.clinique.entity.Validation;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SendValidationNotificationService {

    private final JavaMailSender javaMailSender;

    public void sendValidationCode(Validation validation){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("no-reply@clinique.fr");
            helper.setTo(validation.getUtilisateur().getEmail());
            helper.setSubject("Validation de votre compte");
            String htmlContent = "<!DOCTYPE html>" +
                    "<html lang='fr'>" +
                    "<head>" +
                    "    <meta charset='UTF-8'>" +
                    "    <title>Validation de compte</title>" +
                    "    <style>" +
                    "        body {" +
                    "            font-family: 'Segoe UI', Arial, sans-serif;" +
                    "            background-color: #f4f6f8;" +
                    "            margin: 0;" +
                    "            padding: 0;" +
                    "        }" +
                    "        .container {" +
                    "            max-width: 600px;" +
                    "            margin: 50px auto;" +
                    "            background: #ffffff;" +
                    "            padding: 30px;" +
                    "            border-radius: 12px;" +
                    "            box-shadow: 0 4px 12px rgba(0,0,0,0.08);" +
                    "        }" +
                    "        .header {" +
                    "            text-align: center;" +
                    "            margin-bottom: 20px;" +
                    "        }" +
                    "        .header h2 {" +
                    "            color: #007BFF;" +
                    "            margin: 0;" +
                    "        }" +
                    "        p {" +
                    "            color: #444;" +
                    "            font-size: 16px;" +
                    "            line-height: 1.6;" +
                    "        }" +
                    "        .code-box {" +
                    "            display: inline-block;" +
                    "            font-size: 22px;" +
                    "            font-weight: bold;" +
                    "            padding: 12px 24px;" +
                    "            background: linear-gradient(135deg, #007BFF, #0056b3);" +
                    "            color: #ffffff;" +
                    "            border-radius: 8px;" +
                    "            margin: 25px 0;" +
                    "            letter-spacing: 2px;" +
                    "        }" +
                    "        .footer {" +
                    "            font-size: 14px;" +
                    "            color: #777;" +
                    "            text-align: center;" +
                    "            margin-top: 40px;" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <div class='container'>" +
                    "        <div class='header'>" +
                    "            <h2>Bienvenue !</h2>" +
                    "        </div>" +
                    "        <p>Bonjour,</p>" +
                    "        <p>Merci pour votre inscription 🎉<br>" +
                    "        Pour valider votre compte, utilisez le code ci-dessous :</p>" +
                    "        <div class='code-box'>" + validation.getCode() + "</div>" +
                    "        <p class='footer'>Si vous n'avez pas créé de compte, vous pouvez ignorer cet e-mail.</p>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        }
        catch (Exception e){
            log.error("Error while sending validation code : {}", e.getMessage());
        }
    }

}
