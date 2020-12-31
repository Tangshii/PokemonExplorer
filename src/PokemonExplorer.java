import java.io.IOException;

import javax.swing.JFrame;
public class PokemonExplorer {
	public static void main(String args[]){
		JFrame frame = new JFrame("PokemonExplorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PokemonExplorerPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
