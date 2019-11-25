public class Planet{
	double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
    }
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p1){
		double dx = this.xxPos - p1.xxPos;
		double dy = this.yyPos - p1.yyPos;
		return Math.sqrt((dx * dx)+(dy * dy));
	}
	public double calcForceExertedBy(Planet p1){
		return (G*this.mass*p1.mass)/(this.calcDistance(p1)*this.calcDistance(p1));
	}
	public double calcForceExertedByX(Planet p1){
		return (this.calcForceExertedBy(p1)*(p1.xxPos-this.xxPos)/this.calcDistance(p1));
	}
	public double calcForceExertedByY(Planet p1){
		return (this.calcForceExertedBy(p1)*(p1.yyPos-this.yyPos)/this.calcDistance(p1));
	}
	public double calcNetForceExertedByX(Planet[] allPlanets){
		int i = 0;
		double sum = 0;
		for (i=0; i<allPlanets.length;i+=1 ) {
			if (this.equals(allPlanets[i])) {
				continue;
			}
			sum = sum + this.calcForceExertedByX(allPlanets[i]);
		}
		return sum;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets){
		int i = 0;
		double sum = 0;
		for (i=0; i<allPlanets.length;i+=1 ) {
			if (this.equals(allPlanets[i])) {
				continue;
			}
			sum = sum + this.calcForceExertedByY(allPlanets[i]);
		}
		return sum;
	}
	public void update(double dt,double xForce,double yForce){
		double xAcceleration = xForce/this.mass;
		double yAcceleration = yForce/this.mass;
		this.xxVel = this.xxVel + dt*xAcceleration;
		this.yyVel = this.yyVel + dt*yAcceleration;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}