package com.example.pedidos.exception;

import java.io.Serial;

public class PedidoException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public PedidoException(String message, Exception e) {super(message, e);}

    public PedidoException(String message) {super(message);}
}
