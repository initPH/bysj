package ph;

import com.jfinal.core.JFinal;

public class RunBysj {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8080, "/");
    }
}
