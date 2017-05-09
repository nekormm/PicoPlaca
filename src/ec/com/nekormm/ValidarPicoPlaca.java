/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.nekormm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Jaime Cadena
 */

class Automovil{
    protected String placa;
    
    public Automovil(){}
    
    public Automovil(String placa){
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public boolean puedeTransitar(String fecha, String hora){
        boolean retorno = false;
        if(this.placa != null && this.placa.length() >6 ){
            Date fechaValidar = getDate(fecha, hora);
            if(fechaValidar != null){
                Calendar c = Calendar.getInstance();
                c.setTime(fechaValidar);    
                int dayWeek = c.get(Calendar.DAY_OF_WEEK)-1;
                if(dayWeek !=homologarDigitoDia(Integer.parseInt(this.placa.charAt(this.placa.length()-1)+""))){
                    retorno = true;
                }else{
                    if((fechaValidar.getHours() > 7 && fechaValidar.getHours()<=9) || (fechaValidar.getHours() > 16 && fechaValidar.getHours()<=19)){
                        if(fechaValidar.getHours() == 9 || fechaValidar.getHours() == 19){
                            if(fechaValidar.getMinutes()>=30){
                                retorno = true;
                            }
                        }else if(fechaValidar.getHours() == 16){
                            if(fechaValidar.getHours()>=30)
                                retorno = false;
                        }else{
                            retorno = true;
                        }
                        
                    }else{
                        retorno = true;
                    }
                }
            }
        }
        return retorno;
    }
    
    protected Date getDate(String fecha, String hora){
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try{
            return (inFormat.parse(fecha+hora));
        }catch(Exception e){
            return null;
        }
    }
    
    protected int homologarDigitoDia(int Digito){
        switch(Digito){ 
            case 1: return 1;
            case 2: return 1;
            case 3: return 2;
            case 4: return 2;
            case 5: return 3;
            case 6: return 3;
            case 7: return 4;
            case 8: return 4;
            case 9: return 5;
            case 0: return 5;
        }
        return 0;
    }
}

public class ValidarPicoPlaca {
    public static void main(String[]args){
        Automovil test1 = new Automovil("PBA-2681");
        //Respuesta permite transitar
        System.out.println(test1.puedeTransitar("20170303", "164433"));
        //Respuesta no permite transitar
        System.out.println(test1.puedeTransitar("20170306", "192433"));
        //Respuesta permite transitar
        System.out.println(test1.puedeTransitar("20170306", "194433"));
        //Respuesta permite transitar
        System.out.println(test1.puedeTransitar("20170311", "192433"));
    }
}
