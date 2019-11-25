public class NBody {
	public static double readRadius(String txt){
		In in = new In(txt);
		int rank = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String txt){
		Planet[] pls = new Planet[5];
		In in = new In(txt);
		int rank = in.readInt();
		double radius = in.readDouble();
		int i = 0;
		for (i = 0;i < 5 ;i += 1 ) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet p1 = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
			pls[i] = p1;
		}
		return pls;
	}
	public static void main(String[] args) {
		StdAudio.play("audio/2001.mid");
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		double x0 = (-1)*radius ;
		double y0 = (-1)*radius ;
		double x1 = radius;
		double y1 = radius;
		StdDraw.setXscale(x0, x1);
		StdDraw.setYscale(y0, y1);
		StdDraw.enableDoubleBuffering();
		double t = 0;
		Planet[] p1 = NBody.readPlanets(filename);
		while (t < T){
			int a = 0;
			double[] xForces = new double[5];
			double[] yForces = new double[5];
			for (a = 0; a < 5; a++) {
				xForces[a] = p1[a].calcNetForceExertedByX(p1);
				yForces[a] = p1[a].calcNetForceExertedByY(p1);
			}
		int b = 0;
		for (b = 0; b < 5; b++) {
			p1[b].update(dt,xForces[b],yForces[b]);
		}
		StdDraw.picture(0, 0, "images/starfield.jpg");
		int i = 0;
		for (i = 0; i < 5; i++) {
			p1[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(10);
		T += t;
		}
	}

}