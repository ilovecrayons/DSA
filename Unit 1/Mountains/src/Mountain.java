public class Mountain {
    String rawData = "";
    String[] data = new String[6];

    public Mountain(String rawData) {
        this.rawData = rawData;
        String[] data = rawData.split("\\t");
        for (int i = 0; i < 6; i++) {
            this.data[i] = data[i];
        }
        //convert from format 1234 m to 1234 and 12,345 ft to m
        if(data[5].contains(" ")){
            this.data[5] = data[5].replace(" ", "");
        }
        if(data[5].contains(",")){
            this.data[5] = data[5].replace(",", "");
        }
        if (data[5].contains(" m")) {
            this.data[5] = data[5].replace("m", "");
        }
        if (data[5].contains(" ft")) {
            this.data[5] = data[5].replace("ft", "");
            this.data[5] = String.valueOf(Double.parseDouble(this.data[5]) / 3.28084);
    }


}
