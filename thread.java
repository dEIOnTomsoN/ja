class EvenThread implements Runnable{
    String name;
    Thread t;
    EvenThread(String name){
        this.name=name;
        t=new Thread(this,name);
        System.out.println("New thread: "+t);
        t.start();
    }
    public void run(){
        try{
            for(int i=10;i>0;i--){
                if(i%2==0){
                    System.out.println(name+" : "+i);
                    Thread.sleep(1000);
                }
            }
        }catch(InterruptedException e){
            System.out.println(name + "Interrupted");
        }
    System.out.println(name + " exiting.");
    }
}

class OddThread implements Runnable{
    String string;
    Thread th;
    OddThread(String string){
        this.string = string;
        th = new Thread(this,string);
        System.out.println("New thread: " + th);
        th.start(); 
        }
        public void run() {
                try {
                    for(int i=10;i>0;i--) {
                        if(i%2!=0){
                        System.out.println(string + ": " + i);
                        Thread.sleep(1000);
                        }
                    }
                }catch (InterruptedException e){
                        System.out.println(string + "Interrupted");
            }
                System.out.println(string + " exiting.");
        }
    }

class Main{
            public static void main(String args[]) {
        new EvenThread("Even"); 
        new OddThread("Odd");
        try{
            Thread.sleep(10000);
            }catch (InterruptedException e) {
                System.out.println("Main thread Interrupted");
            }
            System.out.println("Main thread exiting.");
        }
    }
