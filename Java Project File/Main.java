class Main{
	public static void main(String str[]){
        LoginFrame f=new LoginFrame();
        f.addWindowListener(new WindowSensor());
        f.setVisible(true);
    }
}