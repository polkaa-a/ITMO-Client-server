package Connection;

import Common.CommandShell;

import javax.sound.sampled.Port;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.PortUnreachableException;
import java.net.SocketTimeoutException;

public class Receiver {

    private Connection connection;

    public Receiver(Connection con) {
        connection = con;
    }

    public CommandShell receive() throws PortUnreachableException {
        byte[] b = new byte[32 * 1024];
        //инициализация датаграмы (на вход)
        DatagramPacket packet = new DatagramPacket(b, b.length);
        try {
            connection.getSocket().setSoTimeout(30000);
            //получение данных через сокет
            connection.getSocket().receive(packet);
            /*try {
                connection.getSocket().receive(packet);
            } catch (PortUnreachableException e) {
                    if (!connection.getConnection()) return null;
                    else return receive();
            }

             */
            //цепочка потоков (на ввод)
            //ByteArrayInputStream input = new ByteArrayInputStream(b);
            ObjectInputStream objInput = new ObjectInputStream(new ByteArrayInputStream(b));
            //десериализация объекта
            CommandShell command = (CommandShell) objInput.readObject();
            System.out.println("> Response is received");
            return command;
        } catch (ClassNotFoundException e) {
            System.out.println("> Bad command is received");
            return null;
        } catch (SocketTimeoutException e) {
            System.out.println("> Server isn't responding");
            return null;
        }catch (PortUnreachableException e){
            throw new PortUnreachableException();
        } catch (IOException e) {
            System.out.println("> Smth went wrong");
            return null;
        }
    }
}
