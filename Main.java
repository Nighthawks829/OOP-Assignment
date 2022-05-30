public class Main {
    public static void main(String[] args) {
        String fileName = "record.txt";
        CreateFile recordFile = new CreateFile();
        recordFile.createNewFile(fileName);
        Gui mainGui = new Gui();
        mainGui.display();
    }
}