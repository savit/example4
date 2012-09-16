import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class My34 {
	public static void main(String[] args) {
		System.out.println("Программа слушателя нажатия клавиши навигации");
		MyFrame frame=new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//создание окна
class MyFrame extends JFrame{
	public MyFrame(){
		setTitle("Окно программы");
		setSize(300,200);
		MyPanel panel=new MyPanel();
		Container pane=getContentPane();
		pane.add(panel);
	}
}

class MyPanel extends JPanel{
	char c; //атрибут хранения
	int x,y;//координаты ввода
	public MyPanel() {
		c='0';
		x=0;//координаты середины окна
		y=0;//до создания окна координаты окна (0,0)
		MyKey listener=new MyKey();
		addKeyListener(listener);//добавляем слушатель событий клавиши
		setFocusable(true);//устаноить фокус для ввода текста
	}
	public void paintComponent(Graphics g) {
		/*
		 * 10-будем считать, минимальным размером предела, за которые не должна выходить буква
		 * проверка попадания в окно отрисовки нажатой клавиши
		 * присваивание координаты центра окна
		 */
		if (x<10) x=getWidth()/2;
		if (y<10) y=getHeight()/2;
		if (x>getWidth()-10) x=getWidth()-10;
		if (y>getHeight()-10) y=getHeight()-10;
		super.paintComponents(g);
		g.drawString(""+c, x, y);
		
	}
	private class MyKey implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {//нажатие клавиши
			int d=1;
			if (event.isShiftDown()) d=5;
			int k=event.getKeyCode(); 
			if (k==KeyEvent.VK_LEFT) x-=d;
			if (k==KeyEvent.VK_RIGHT) x+=d;
			if (k==KeyEvent.VK_UP) y-=d;
			if (k==KeyEvent.VK_DOWN) y+=d;
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent event) {//клавиша отпущена
			System.out.println("Released="+event.getKeyCode());			
		}

		@Override
		public void keyTyped(KeyEvent event) {//значение клавиши большая/маленькая
			c=event.getKeyChar();
			repaint();//перерисовать окно???
		}
		
	}
}