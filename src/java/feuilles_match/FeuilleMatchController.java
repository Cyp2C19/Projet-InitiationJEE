 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cyprien
 */
@Named(value = "feuilleMatchController")
@ViewScoped
public class FeuilleMatchController implements Serializable{
    @EJB
    private FeuilleMatchDAO dao;
    private FeuilleMatch feuilleMatchSelect;
    /**
     * Creates a new instance of FeuilleMatchController
     */
    public FeuilleMatchController() {
        this.feuilleMatchSelect = new FeuilleMatch();
    }    
  
    public FeuilleMatchDAO getDao() {
        return dao;
    }

    public void setDao(FeuilleMatchDAO dao) {
        this.dao = dao;
    }

    public FeuilleMatch getFeuilleMatchSelect() {
        return feuilleMatchSelect;
    }

    public void setFeuilleMatchSelect(FeuilleMatch feuilleMatchSelect) {
        this.feuilleMatchSelect = feuilleMatchSelect;
    }
    
    public List<FeuilleMatch> getFeuillesMatch(){
        return dao.getAll();
    }
    
    public String suppFeuilleMatch(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.feuilleMatchSelect == null){
            context.addMessage(null, 
                new FacesMessage("Veuillez sélectionner une feuille de match ! "));
            return null;
        }
        dao.suppFeuilleMatch(this.feuilleMatchSelect);
        deletePDF(this.feuilleMatchSelect.toString());
        return null;
    }
            
    public String creerFeuilleMatch(FeuilleMatch feuille) throws DocumentException, IOException{
            FacesContext context = FacesContext.getCurrentInstance();
            if (feuille.getEquipeDomicile() == null ||
                feuille.getEquipeExterieur() == null ) {
                context.addMessage(null, 
                new FacesMessage("Veuillez sélectionner deux équipes ! "));
                return null;
            }
            if (feuille.getEquipeDomicile().getTitulaires().size() != 11 ||
                feuille.getEquipeExterieur().getTitulaires().size() != 11) {
                context.addMessage(null, 
                new FacesMessage("Veuillez sélectionner 11 titulaires pour chaque équipe !"));
                return null;
            }
            feuille = dao.creerFeuilleMatch(feuille);
            toPDF(feuille.toString());
        return "feuilles-de-match";
    }
    
    public void deletePDF(String idFeuille){
        File file = new File("C:\\Users\\cyprien\\Documents\\NetBeansProjects\\Projet\\web\\resources\\pdf\\fm" + idFeuille + ".pdf");
        file.delete();
    }
    
    public void toPDF(String idFeuille) throws FileNotFoundException, DocumentException, IOException{
            
            //Create Document instance.
	Document document = new Document();
        
	//Create OutputStream instance.
	OutputStream outputStream = 
		new FileOutputStream(new File("C:\\Users\\cyprien\\Documents\\NetBeansProjects\\Projet\\web\\resources\\pdf\\fm" + idFeuille + ".pdf"));
	//Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);
 
        //Open the document.
        document.open();
  
        //Create Paragraph
        Paragraph infosMatch = new Paragraph("Feuille de Match n° " + idFeuille, new Font(Font.FontFamily.UNDEFINED, 18,
                  Font.BOLD));
        
        infosMatch.add(new Paragraph(" "));
        
        SimpleDateFormat datePattern = new SimpleDateFormat("dd-MM-yyyy");
        infosMatch.add(new Paragraph("Date de match : " + datePattern.format(feuilleMatchSelect.getDateMatch())));
        infosMatch.add(new Paragraph("Arbitre : " + feuilleMatchSelect.getArbitre()));
        infosMatch.add(new Paragraph("Stade : " + feuilleMatchSelect.getStade()));

        //New line
        infosMatch.add(new Paragraph(" "));
        document.add(infosMatch);
        
        //Create Paragraph
        Paragraph domHeader = new Paragraph("Domicile : " + feuilleMatchSelect.getEquipeDomicile().getNom(), new Font(Font.FontFamily.UNDEFINED, 13,
                  Font.BOLD));
        domHeader.add(new Paragraph(" "));
        domHeader.add("Coach : " + feuilleMatchSelect.getEquipeDomicile().getCoach());
        domHeader.add(new Paragraph(" "));
        
        Paragraph extHeader = new Paragraph("Extérieur : " + feuilleMatchSelect.getEquipeExterieur().getNom(), new Font(Font.FontFamily.UNDEFINED, 13,
                  Font.BOLD));
        extHeader.add(new Paragraph(" "));
        extHeader.add("Coach : " + feuilleMatchSelect.getEquipeExterieur().getCoach());
        extHeader.add(new Paragraph(" "));
        
        
        PdfPTable head = new PdfPTable(2);
        head.setWidthPercentage(100);
        
        PdfPCell lcell = new PdfPCell();
        lcell.setPadding(0);
        lcell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        lcell.setBorder(PdfPCell.NO_BORDER);
        lcell.addElement(domHeader);
        
        PdfPCell rcell = new PdfPCell();
        rcell.setPadding(0);
        rcell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        rcell.setBorder(PdfPCell.NO_BORDER);
        rcell.addElement(extHeader);
        
        head.addCell(lcell);
        head.addCell(rcell);
        
        document.add(head);
        
        
        // Main table
        PdfPTable mainTable = new PdfPTable(2);
        mainTable.setWidthPercentage(100.0f);
        
        //Create a table in PDF
        PdfPCell firstTableCell = new PdfPCell();
        firstTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable domicile = new PdfPTable(3);
        domicile.setWidths(new int[]{1, 5, 1});
        PdfPCell cell = new PdfPCell(new Phrase("Titulaires",new Font(Font.FontFamily.UNDEFINED, 15,
                  Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        domicile.addCell(cell);

        domicile.setHeaderRows(1);

        for (Joueur j : feuilleMatchSelect.getEquipeDomicile().getTitulaires()){
            domicile.addCell(String.valueOf(j.getNumero()));
            domicile.addCell(j.getPrenom() + " " + j.getNom());
            domicile.addCell(j.getPoste());
        }
        
        PdfPCell cell1 = new PdfPCell(new Phrase("Remplaçants",new Font(Font.FontFamily.UNDEFINED, 15,
                  Font.BOLD)));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setColspan(3);
        domicile.addCell(cell1);
        
        for (Joueur j : feuilleMatchSelect.getEquipeDomicile().getRemplacants()){
            domicile.addCell(String.valueOf(j.getNumero()));
            domicile.addCell(j.getPrenom() + " " + j.getNom());
            domicile.addCell(j.getPoste());
        }
        
        //Create a table in PDF
        PdfPCell secondTableCell = new PdfPCell();
        secondTableCell.setBorder(PdfPCell.NO_BORDER);
        PdfPTable exterieur = new PdfPTable(3);
        exterieur.setWidths(new int[]{1, 5, 1});
        exterieur.addCell(cell);
        exterieur.setHeaderRows(1);

        for (Joueur j : feuilleMatchSelect.getEquipeExterieur().getTitulaires()){
            exterieur.addCell(String.valueOf(j.getNumero()));
            exterieur.addCell(j.getPrenom() + " " + j.getNom());
            exterieur.addCell(j.getPoste());
        }
        
        exterieur.addCell(cell1);
        
        for (Joueur j : feuilleMatchSelect.getEquipeExterieur().getRemplacants()){
            exterieur.addCell(String.valueOf(j.getNumero()));
            exterieur.addCell(j.getPrenom() + " " + j.getNom());
            exterieur.addCell(j.getPoste());
        }

        firstTableCell.addElement(domicile);
        mainTable.addCell(firstTableCell);
        secondTableCell.addElement(exterieur);
        mainTable.addCell(secondTableCell);
        
        document.add(mainTable);

        document.close();
        outputStream.close();
    }
       
}
