package br.ufsm.csi.app.utils;

public class DefaultResponse {

    public DefaultResponse(String msg, Number status){
        this.Message = msg;
        this.Status = status;
    }
    public String Message;
    public Number Status;
}
