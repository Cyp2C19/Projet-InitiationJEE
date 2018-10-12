/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;
 
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
 
 
@FacesConverter("equipeConverter")
public class EquipeConverter implements Converter {

    
    public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
        if (uic == null || !value.matches("[0-9]+")) {
            return null;
        }
        ValueExpression vex = ctx.getApplication().getExpressionFactory().
        createValueExpression(ctx.getELContext(),"#{equipeController}", EquipeController.class);
        EquipeController ctrl = (EquipeController)vex.getValue(ctx.getELContext());
        Equipe eq = ctrl.getEquipeById(Integer.valueOf(value));
        return eq;
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Equipe) object).getIdEquipe());
        }
        else {
            return null;
        }
    }   
}         
     
