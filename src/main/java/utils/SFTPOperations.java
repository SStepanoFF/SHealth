package utils;

import com.jcraft.jsch.*;
import framework.GlobalVariables;


import java.io.File;

/**
 * Created by sergii.stepanov on 04/08/2015.
 */
public class SFTPOperations {

    public static final void downloadFilesToSFTP() {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel=null;
        try {
            //open session
            session = jsch.getSession("sutterhealthw", "sftp.keysurvey.com", 22);

            //configure authentication
            session.setConfig(Loader.loadProperty("sftpLog"), Loader.loadProperty("sftpPass"));
            session.setPassword(Loader.loadProperty("sftpPass"));
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            //open sftp channel
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
            for (File file: new File(GlobalVariables.TEMP_FILE_PATH).listFiles() ) {
                sftpChannel.put(file.getPath(), "/incoming");
                sftpChannel.stat("/incoming/" + file.getName());    //verify file exists in SFTP
                System.out.println(String.format("File %s is downloaded correctly!",file.getName()));
            }
        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connect to SFTP server!");
        }
        catch (SftpException e) {
            e.printStackTrace();
            throw new RuntimeException("Files were NOT downloaded to SFTP server!");
        }finally {
            sftpChannel.exit();
            session.disconnect();
        }
    }
}
