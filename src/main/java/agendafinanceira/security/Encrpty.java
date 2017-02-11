package agendafinanceira.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import agendafinanceira.services.exception.HashMD5Exception;

public class Encrpty {
	
    // Função para criar hash MD5 da senha informada 
    // Autor: Madson Costa Carvalho (Devmedia)
    // Contribuição: Álvaro Rocha (Devmedia)
    // Adaptação: Osmar Filho
    
    public static String getMD5(String s) throws HashMD5Exception {

        String senhaMD5 = "";
        MessageDigest m;

        try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, m.digest());

            //Formatando o resuldado em uma cadeia de 32 caracteres, 
            //completando com zeros a esquerda 
            senhaMD5 = String.format("%1$032X", i);
        } catch (NoSuchAlgorithmException e) {
            throw new HashMD5Exception(e.getMessage());
        }
        return senhaMD5;

    }


}
