/**
 * 
 */
package cn.com.sure.socket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import cn.com.sure.km.KmApplicationexception;

/**
 * @author Limin
 *
 */
public interface SocketService {


	byte[] handleSocket(String requestInfo) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ParseException,KmApplicationexception,InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

}
