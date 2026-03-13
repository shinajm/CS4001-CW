public class MP3 extends Gadget {

    private int memory;

    public MP3(String model, double price, int weight, String size, int memory) {
        super(model, price, weight, size);
        this.memory = memory;
    }

    public int getMemory() {
        return memory;
    }

    public void downloadMusic(int downloadSize) {
        if (downloadSize <= 0) {
            System.out.println("Download size must be positive.");
            return;
        }

        if (memory >= downloadSize) {
            memory -= downloadSize;
            System.out.println("Downloaded " + downloadSize + " memory.");
            System.out.println("Remaining memory: " + memory);
        } else {
            System.out.println("Error: Not enough available memory to download.");
        }
    }

    public void deleteMusic(int deleteSize) {
        if (deleteSize <= 0) {
            System.out.println("Delete size must be positive.");
            return;
        }

        memory += deleteSize;
        System.out.println("Deleted " + deleteSize + " memory.");
        System.out.println("Available memory: " + memory);
    }

    public void display() {
        super.display();
        System.out.println("Available memory: " + memory);
    }
}