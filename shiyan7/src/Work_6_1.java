import java.io.*;
import java.util.HashSet;
import java.util.Set;

interface IPProcess {
    void process(File in, File out) throws IOException;
}

class IPProcess1 implements IPProcess {
    @Override
    public void process(File in, File out) throws IOException {
        /*
        * BufferedReader, read()方法会先从缓冲区中进行读取。如果缓冲区数据不足，才会再从文件中读取
        * */
        String input;
        Set<String> set = new HashSet<>();                                                                    //HashSet去重
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(in)));   //缓冲区
        FileWriter writer = new FileWriter(out);
        while ((input = bufferedReader.readLine()) != null) set.add(input);       //读文件
        for (String str : set) writer.write(str + "\n");                      //写文件
        bufferedReader.close();
        writer.close();
    }
}

class IPProcess2 implements IPProcess {
    private final byte[] mask = new byte[]{1, 2, 4, 8, 16, 32, 64, (byte) 128};

    @Override
    public void process(File in, File out) throws IOException {
        int i, j;
        String input;
        String[] num;
        byte[] bits = new byte[536870912];                           //限制1GB内存
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(in))); //向缓冲区中读文件
        FileWriter writer = new FileWriter(out);                     //FileWriter:按字符向流中写入数据
        while ((input = bufferedReader.readLine()) != null) {
            num = input.split("\\.");
            /*
            <<  表示左移，不分正负数，低位补0
            >>  表示右移，如果该数为正，则高位补0，若为负数，则高位补1
            >>> 表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
             */
            System.out.println(Integer.parseInt(num[0])<<21);
            i = (Integer.parseInt(num[0]) << 21) +
                    (Integer.parseInt(num[1]) << 13) +
                    (Integer.parseInt(num[2]) << 5) +
                    (Integer.parseInt(num[3]) >> 3);
            bits[i] = (byte) (bits[i] | mask[Integer.parseInt(num[3]) % 8]);
        }
        for (i = 0; i < 536870912; i++)
            for (j = 0; j < 8; j++)
                if ((bits[i] & mask[j]) == mask[j])
                    //255->11111111，一个字节
                    writer.write(String.format("%d.%d.%d.%d\n", (i >>> 21) & 255, (i >>> 13) & 255, (i >>> 5) & 255, ((i << 3) | j) & 255));
        bufferedReader.close();
        writer.close();
    }
}

class IPProcess3 implements IPProcess {
    @Override
    public void process(File in, File out) throws IOException {
        String input;
        String compare;
        boolean isExist;
        RandomAccessFile readFile = new RandomAccessFile(in, "r");              //可从任意位置访问文件
        RandomAccessFile writeFile = new RandomAccessFile(out, "rw");
        readFile.seek(0);   //读取文件指针开始位置
        while ((input = readFile.readLine()) != null) {
            isExist = false;
            writeFile.seek(0);                                                   //写指针定位
            while ((compare = writeFile.readLine()) != null)
                if (input.equals(compare)) {                                     //判断是否重复，重复则跳过，不重复写入文件
                    isExist = true;
                    break;
                }
            if (!isExist) {
                writeFile.seek(writeFile.length());
                writeFile.writeBytes(input + "\n");
            }
        }
        readFile.close();
        writeFile.close();
    }
}

public class Work_6_1 {
    static public String inPath;
    static public String outPath_1;
    static public String outPath_2;
    static public String outPath_3;

    static {
        inPath = "D:\\notebook\\AfterclassWork\\Java\\Work6_in.txt";
        outPath_1 = "D:\\notebook\\AfterclassWork\\Java\\Work6_out_1.txt";
        outPath_2 = "D:\\notebook\\AfterclassWork\\Java\\Work6_out_2.txt";
        outPath_3 = "D:\\notebook\\AfterclassWork\\Java\\Work6_out_3.txt";
    }

    public static void main(String[] args) {
        File in = new File(inPath);
        File out_1 = new File(outPath_1);
        File out_2 = new File(outPath_2);
        File out_3 = new File(outPath_3);
        IPProcess ipProcess_1 = new IPProcess1();
        IPProcess ipProcess_2 = new IPProcess2();
        IPProcess ipProcess_3 = new IPProcess3();
        try {
            ipProcess_1.process(in, out_1);
            ipProcess_2.process(in, out_2);
            ipProcess_3.process(in, out_3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕！");
    }
}
