package cn.flashsale.component.captcha;

import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * description...
 *
 * @author linyan-z7
 * @date 2024/6/13
 */
public class AppiumBasicCaptchaSolver {

    /*private AndroidDriver driver;

    private AppiumBasicCaptchaSolver() {
    }

    public AppiumBasicCaptchaSolver(AndroidDriver driver) {
        this.driver = driver;
    }*/

    static {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //System.load("opencv-4.10.0-win-dll/opencv_java_x64.dll");
    }

    public static void main(String[] args) {
        // 加载 OpenCV库
        System.loadLibrary("lib/opencv_java4100_winx64");

        // 加载模型和配置文件
        String modelWeights = "path/to/your/model.weights";
        String modelConfig = "path/to/your/model.cfg";
        Net net = Dnn.readNetFromDarknet(modelConfig, modelWeights);

        // 加载图像
        Mat image = Imgcodecs.imread("Z:\\wait.png");

        // 将图像转换为 blob
        Mat blob = Dnn.blobFromImage(image, 1.0, new Size(416, 416), new Scalar(0, 0, 0), true, false);

        // 设置输入图像
        net.setInput(blob);

        // 运行前向传播
        Mat detectionMat = net.forward();

        // 解析检测结果
        for (int i = 0; i < detectionMat.rows(); i++) {
            double confidence = detectionMat.get(i, 5)[0];
            if (confidence > 0.5) {
                int x = (int) (detectionMat.get(i, 0)[0] * image.cols());
                int y = (int) (detectionMat.get(i, 1)[0] * image.rows());
                int width = (int) (detectionMat.get(i, 2)[0] * image.cols());
                int height = (int) (detectionMat.get(i, 3)[0] * image.rows());

                Imgproc.rectangle(image, new Point(x, y), new Point(x + width, y + height), new Scalar(0, 255, 0), 2);
            }
        }

        // 保存处理后的图片
        Imgcodecs.imwrite(new File("Z:\\ok.png").getAbsolutePath(), image);
    }

}
