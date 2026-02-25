/**
 * This program is a demonstration of the Proxy design pattern.
 * It controls access to a RealFileService based on the user's role.
 */

package ioprogramming.designpatterns;

interface FileService {
    void accessFile();
}

class RealFileService implements FileService {
    public void accessFile() {
        System.out.println("Accessing sensitive server files...");
    }
}

class FileAccessProxy implements FileService {
    private RealFileService realService;
    private String userRole;

    public FileAccessProxy(String role) { this.userRole = role; }

    @Override
    public void accessFile() {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            if (realService == null) realService = new RealFileService();
            realService.accessFile();
        } else {
            System.out.println("Access Denied: You do not have permission.");
        }
    }
}

public class ProxyDemonstration {
    public static void main(String[] args) {
        FileService adminProxy = new FileAccessProxy("ADMIN");
        FileService userProxy = new FileAccessProxy("USER");

        System.out.print("Admin Request: ");
        adminProxy.accessFile();

        System.out.print("User Request: ");
        userProxy.accessFile();
    }
}