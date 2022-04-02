package rarible_e;

import java.io.File;
import java.util.*;

public class generateConfigurator {

    public static String path = "";
    static String inputPATH(){
        System.out.print("Input Path To Be Generate : ");
        Scanner sc = new Scanner(System.in);
        return  sc.nextLine().toString();
    }
    static List<File> getNameFolder(){
        File[] directories = new File(path).listFiles(File::isDirectory);
        List<File> s = Arrays.stream(directories).toList();

        return s;
    }
    static void printDirectoryName(List<String> file){
        System.out.printf("Total Directory : %d%n",file.size());
        for(String fp: file){
            System.out.printf("Folder  %s'%s'%s %n", Color.YELLOW,
                    fp,Color.RESET);
        }
    }
    static List<String> parseList(List<File> folderName){
        String dirData[] = new String[folderName.size()];
        List<String> fixedData = new ArrayList<>();
        int index = 0;
        for(File fps : folderName){
            dirData[index] = fps.getName();
            index++;
        }
        List<helperFolder> listHelper = new ArrayList<>();
        for(int y =0; y < dirData.length; y++){
            String text = dirData[y];
            String splitData[] = text.split("_");
            listHelper.add(new helperFolder(splitData[1],Integer.parseInt(splitData[0])));
        }
        //sort
        for(int b = 0; b < listHelper.size(); b++){
            for(int y = 0; y < listHelper.size() - b - 1; y++){
                if(listHelper.get(y + 1).getIndex() < listHelper.get(y).getIndex()){
                    helperFolder folderOBJ_TEMPORARY = listHelper.get(y + 1);
                    listHelper.set(y + 1 , listHelper.get(y));
                    listHelper.set(y,folderOBJ_TEMPORARY);
                }
            }
        }
        //merge
        for(int t = 0 ;t < listHelper.size(); t++){
            int indexPath = listHelper.get(t).getIndex();
            String namePath = listHelper.get(t).getNameFolder();
            fixedData.add(String.valueOf(indexPath) + "_"+namePath);
        }

        return fixedData;
    }
    static void generateLayerCODE(List<String> fileName){
        int totalGenerate = 512;
        System.out.printf("const layerConfigurations = [   %n { %n growEditionSizeTo: %d, %n layersOrder: [%n"
        ,totalGenerate);
        for(String nameFolder : fileName){
            System.out.printf("{name: \"%s\"},%n", nameFolder);
        }
        System.out.printf("     ],%n  },%n];");
    }
    public static void main(String[]args){
        path = inputPATH();
        parseList(getNameFolder());
        generateLayerCODE(parseList(getNameFolder()));
        /*
        Input Path To Be Generate : D:\nft\virtual waifu\layer
const layerConfigurations = [
 {
 growEditionSizeTo: 512,
 layersOrder: [
{name: "0_bg"},
{name: "1_base"},
{name: "2_accesoris"},
{name: "3_accesoris"},
{name: "4_horn"},
{name: "5_clothes"},
{name: "6_clothes"},
{name: "7_eye"},
{name: "8_fxeye"},
{name: "9_eyebrow"},
{name: "10_earring"},
{name: "11_pose"},
{name: "12_head"},
{name: "13_mouth"},
{name: "14_noise"},
     ],
  },
];
         */
    }
}
