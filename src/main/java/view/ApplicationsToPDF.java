package view;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import controller.HomeController;
import shared.ApplicationDTO;
import shared.DateDTO;
import shared.ExperienceDTO;
import shared.PublicApplicationDTO;

import java.io.FileOutputStream;
import java.util.List;

public class ApplicationsToPDF {
    public static void main(String[] args) {
        HomeController hc = new HomeController();

        Document document = new Document();
        List<PublicApplicationDTO> applications = hc.getApplicants();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("./pdf/Applications.pdf"));
            document.open();
            Font h1 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font h2 = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Font p1 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

            for(PublicApplicationDTO application : applications){
                Paragraph header = new Paragraph("Application for: " + application.getName() + " " + application.getSurname(), h1);
                document.add(header);

                Paragraph info = new Paragraph("SSN: " + application.getSsn() +
                ", Email: " + application.getEmail() +
                ", Status: " + application.getHired() +
                ", Reg. date: " + application.getRegistrationdate()
                );
                document.add(info);

                Paragraph experienceHeader = new Paragraph("Experiences: ", h2 );
                document.add(experienceHeader);

                for(ExperienceDTO experience : application.getExperiences()){
                    Paragraph experienceParagraph = new Paragraph(experience.getName() +
                    " for " + experience.getYears() + " years.", p1);
                    document.add(experienceParagraph);
                }

                Paragraph availabilityHeader = new Paragraph("Available: ", h2);
                document.add(availabilityHeader);

                for(DateDTO dates : application.getAvailabilities()){
                    Paragraph datesParagraph = new Paragraph("From " + dates.getStart() + " to " + dates.getEnd(), p1);
                    document.add(datesParagraph);
                }

                Paragraph blank = new Paragraph(" ", h1);
                document.add(blank);

            }

            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
