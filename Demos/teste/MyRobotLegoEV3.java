package Demos.teste;

public class MyRobotLegoEV3 {
	
	public boolean Open(String Name) {
		System.out.printf( "Abrir o Robot com o nome <%s>\n",Name);
		return true;
		
		/*
		 * %s-string
		 * %d-...
		 * %f-...
		 * %c-...
		 * %b-...
		 * %x-...
		 */
	}
	/**public void Close() {
		System.out.printf( "Fechar o Robot com o nome\n");
		
	}**/

	public void LeftTurn(int radius, int degree) {
		System.out.printf( "LeftTurn(%d, %d)\n",radius, degree);
	}
	
	public void RightTurn(int radius, int degree) {
		System.out.printf( "RightTurn(%d, %d)\n",radius, degree);
		
	}
	
	public void Strait(int distancia) {
		System.out.printf("Strait(%d)\n", distancia);
	}
	
	public void Back(int distancia) {
		System.out.printf("Back(%d)\n", distancia);
	}
	
	public void Stop(boolean stop) {
		System.out.printf("Parar o robot!\n");
	}
	
	/*
	 * for (int i = 0; i<4;i++){
	 * robot.rotate(20);
	 * robot.curvaEsquerda(0,90);
	 * }
	 * robot.parar;
	 */

}
