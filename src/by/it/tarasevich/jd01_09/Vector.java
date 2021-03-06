package by.it.tarasevich.jd01_09;



import java.util.Arrays;

class Vector extends Var {

    private double[] value;
    public double[] getValue(){return value;}

    public Vector(double[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public Vector(Vector vector) {
        this.value = vector.value;

    }

    public Vector(String str) {
        StringBuilder sb= new StringBuilder(str);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length()-1);
        str=sb.toString();
        String []arrayString=str.split(",");

        this.value= new double[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            value[i]=Double.parseDouble(arrayString[i]);
        }
    }



    @Override
    public Var add(Var other) {

        if (other instanceof Scalar){
            double [] res=Arrays.copyOf(value,value.length);
            for (int i = 0; i < res.length; i++) {
                res[i]=res[i]+ ((Scalar) other).getValue();
            }
            return new Vector(res);
        }
        else if (other instanceof Vector){
            double [] res=Arrays.copyOf(value,value.length);
            for (int i = 0; i < res.length; i++) {
                res[i]=res[i]+((Vector) other).value[i];
            }
            return new Vector(res);
        }
        else return super.add(other);
    }

    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar){
            double [] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length ; i++) {
                res[i] -= ((Scalar)other).getValue();
            }
            return new Vector(res);
        }
        else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] - ((Vector) other).value[i];
            }
            return new Vector(res);
        }
        else return super.add(other);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar){
            double [] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length ; i++) {
                res[i] *= ((Scalar)other).getValue();
            }
            return new Vector(res);
        }
        else if (other instanceof Vector){
            double [] res=Arrays.copyOf(value,value.length);
            double sum=0;
            for (int i = 0; i < res.length; i++) {
                sum=sum+res[i]*((Vector) other).value[i];
            }
            return new Scalar(sum);
        }
        else return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Scalar){
            double [] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length ; i++) {
                res[i] /= ((Scalar)other).getValue();
            }
            return new Vector(res);
        }
        else if (other instanceof Vector){  return super.div(other);}
        else if (other instanceof Matrix){  return super.div(other);}
        else  return super.div(other);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        String space = "";
        for (double v : value) {
            stringBuilder.append(space).append(v);
            space = ", ";

        }
        stringBuilder.append("}");


        return stringBuilder.toString();
    }
}
