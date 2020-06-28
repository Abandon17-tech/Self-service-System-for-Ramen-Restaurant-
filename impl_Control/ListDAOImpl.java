package impl_Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import vo_Entity.Ramen;
import vo_Entity.SideDish;

/** 
 * This class is the hub where the order list information is performed
 * @author Yanwei Hua
 * @version 1.1
 * 
 */
public class ListDAOImpl {

	ArrayList<Ramen> list = new ArrayList<Ramen>();
	static Ramen ramen;

	String filepath = "src/orderList.txt";

	/**
	 * This method is used to get all list information from database
	 * @param id order's serial number
	 * @return ramen object
	 * @throws IOException
	 */
	public ArrayList<Ramen> getList(String id) throws IOException {

		FileReader fReader = new FileReader(filepath);
		BufferedReader bReader = new BufferedReader(fReader);

		String temp = bReader.readLine();

		while (temp != null) {
			if (temp == "") {
				System.out.println("Warning! Check orderList.txt.");
				break;
			}
			String seg[] = temp.split(",");
			

			if (seg[2].equals(id)) {
				Ramen ramen = new Ramen();
				ramen.setSerialNumer(seg[0]);
				ramen.setTime(seg[1]);
				ramen.setId(seg[2]);
				ramen.soup = new SideDish("soup", seg[3]);
				//System.out.println("soup: " + ramen.soup.getType());
				ramen.noodle = new SideDish("noodle", seg[4]);
				ramen.onion = new SideDish("onion", seg[5]);
				ramen.spiciness = Integer.valueOf(seg[6]);
				if (!seg[7].equals("0"))
					ramen.nori.setIsSelected(true);
				if (!seg[8].equals("0"))
					ramen.boiledEgg.setIsSelected(true);
				if (!seg[9].equals("0"))
					ramen.bamboo.setIsSelected(true);
				if (!seg[10].equals("0"))
					ramen.chashu.setIsSelected(true);
				
				ramen.nori.setQuantity(Integer.valueOf(seg[7]));
				ramen.boiledEgg.setQuantity(Integer.valueOf(seg[8]));
				ramen.bamboo.setQuantity(Integer.valueOf(seg[9]));
				ramen.chashu.setQuantity(Integer.valueOf(seg[10]));
				
				ramen.price = Double.valueOf(seg[11]);
				ramen.setDiningMethod(seg[12]);
				ramen.setPayingMethod(seg[13]);
				
				list.add(ramen);
			}
			temp = bReader.readLine();
		}

		bReader.close();
		fReader.close();
		return list;
	}
	
	/**
	 * This method is used to write new order to txt file(database)
	 * @param ramen ramen object
	 * @throws IOException
	 */
	public void writeList(Ramen ramen) throws IOException {
		String seg[] = new String[14];
		for (int i = 0; i<14; i++) {
			seg[i] = new String();
		}
		
		seg[0] = ramen.getSerialNumer();
		seg[1] = ramen.getTime();
		seg[2] = ramen.getId();
		seg[3] = ramen.soup.getType();
		seg[4] = ramen.noodle.getType();
		seg[5] = ramen.onion.getType();
		seg[6] = String.valueOf(ramen.spiciness);
		
		seg[7] = String.valueOf(ramen.nori.getQuantity());
		seg[8] = String.valueOf(ramen.boiledEgg.getQuantity());
		seg[9] = String.valueOf(ramen.bamboo.getQuantity());
		seg[10] = String.valueOf(ramen.chashu.getQuantity());
		
		seg[11] = String.valueOf(ramen.price);
		seg[12] = ramen.getDiningMethod();
		seg[13] = ramen.getPayingMethod();
		
//		for (int i = 0; i<14; i++) {
//			System.out.println(seg[i]);
//		}File f = new File(filepath);
		
		FileWriter fw = null;
		File f = new File(filepath);
		fw = new FileWriter(f, true);
		
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i<14; i++) {
			if (i == 0) pw.print("\n");
			pw.print(seg[i]);
			if (i != 13) pw.print(",");
		}
		pw.flush();
		
		fw.flush();
		pw.close();
		fw.close();

		
	}

	public String getPastDate() throws ParseException {  
		Calendar calendar1 = Calendar.getInstance();  
        Calendar calendar2 = Calendar.getInstance();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;  
        int offset1 = 1 - dayOfWeek;  
        int offset2 = 7 - dayOfWeek;  
        
        calendar1.add(Calendar.DATE, offset1 - 7);  
        calendar2.add(Calendar.DATE, offset2 - 7);  
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday  
        String lastBeginDate = sdf.format(calendar1.getTime());  
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday  
        String lastEndDate = sdf.format(calendar2.getTime());  
        return lastBeginDate + "," + lastEndDate;  
	}  
	
	public Map<String, Map<String, Integer>> getSalesNum() throws IOException, ParseException {
		FileReader fReader = new FileReader(filepath);
		BufferedReader bReader = new BufferedReader(fReader);

		String temp = bReader.readLine();
		int total = 0;
		int Tonkotsu = 0; int Shoyu = 0; int Shio = 0;
		int Firm = 0; int Medium = 0; int Soft = 0;
		int No = 0; int Little = 0; int Lot = 0;
		int spiciness_0 = 0; int spiciness_1 = 0; int spiciness_2 = 0; int spiciness_3 = 0; int spiciness_4 = 0; int spiciness_5 = 0;
		int Nori = 0; int BoiledEgg = 0; int Chashu = 0; int BambooShoots = 0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Integer> soup = new HashMap<String, Integer>();
		Map<String, Integer> noodle = new HashMap<String, Integer>();
		Map<String, Integer> onion = new HashMap<String, Integer>();
		Map<String, Integer> spiciness = new HashMap<String, Integer>();
		Map<String, Integer> addOn = new HashMap<String, Integer>();
		
		Map<String, Map<String, Integer>> data = new HashMap<String, Map<String,Integer>>();

		while (temp != null) {
			if (temp == "") {
				System.out.println("Warning! Check orderList.txt.");
				break;
			}
			String seg[] = temp.split(",");
 
			Date result = format.parse(seg[1]); 
			String timeInterval = getPastDate();
			Date beginTime = format.parse(timeInterval.split(",")[0]);
			Date endTime = format.parse(timeInterval.split(",")[1]);
			
			System.out.println(beginTime + " " + endTime);

			if (result.before(endTime) && beginTime.before(result) || (endTime.compareTo(result) == 0 || beginTime.compareTo(result) == 0)) {
				total++;
				
				if (seg[3].equals("Tonkotsu")) {
					Tonkotsu++;
				} else if (seg[3].equals("Shoyu")) {
					Shoyu++;
				} else if (seg[3].equals("Shio")) {
					Shio++;
				}
				
				if (seg[4].equals("Firm")) {
					Firm++;
				} else if (seg[4].equals("Soft")) {
					Soft++;
				} else if (seg[4].equals("Medium")) {
					Medium++;
				}
				
				if (seg[5].equals("No")) {
					No++;
				} else if (seg[5].equals("A little")) {
					Little++;
				} else if (seg[5].equals("A lot")) {
					Lot++;
				}
				
				if (Integer.valueOf(seg[6]) == 0) {
					spiciness_0++;
				} else if (Integer.valueOf(seg[6]) == 1) {
					spiciness_1++;
				} else if (Integer.valueOf(seg[6]) == 2) {
					spiciness_2++;
				} else if (Integer.valueOf(seg[6]) == 3) {
					spiciness_3++;
				} else if (Integer.valueOf(seg[6]) == 4) {
					spiciness_4++;
				} else if (Integer.valueOf(seg[6]) == 5) {
					spiciness_5++;
				} 
				
				if (seg[7].equals("1"))
					Nori++;
				if (seg[8].equals("1"))
					BoiledEgg++;
				if (seg[9].equals("1"))
					Chashu++;
				if (seg[10].equals("1"))
					BambooShoots++;
			}
			temp = bReader.readLine();
		}
		bReader.close();
		fReader.close();
		
		soup.put("Tonkotsu", Tonkotsu); 
		soup.put("Shoyu", Shoyu); 
		soup.put("Shio", Shio); 
		noodle.put("Soft", Soft);
		noodle.put("Medium", Medium);
		noodle.put("Firm", Firm);
		onion.put("No", No);
		onion.put("A Little", Little);
		onion.put("A Lot", Lot);
		spiciness.put("0", spiciness_0);
		spiciness.put("1", spiciness_1);
		spiciness.put("2", spiciness_2);
		spiciness.put("3", spiciness_3);
		spiciness.put("4", spiciness_4);
		spiciness.put("5", spiciness_5); 
		addOn.put("Nori", Nori);
		addOn.put("Boiled egg", BoiledEgg);
		addOn.put("Chashu", Chashu);
		addOn.put("Bamboo shoots", BambooShoots);
		
		data.put("Soup",soup);
		data.put("Noodle",noodle);
		data.put("Onion",onion);
		data.put("Spiciness",spiciness);
		data.put("Add On",addOn);

		return data;
	}
	
	/**
	 * This method is used to create data chart of sales
	 * @param title chart's title
	 * @param datas chart's listed data
	 * @param type chart's type
	 * @param danwei chart's unit
	 * @param font chart's font
	 * @throws IOException
	 */
    public void createPort(String title,Map<String,Map<String,Integer>> datas,String type,String danwei,Font font) throws IOException{
    	Calendar calendar = Calendar.getInstance();
    	Date today = calendar.getTime(); 
    	
    	
        DefaultCategoryDataset ds = new DefaultCategoryDataset();

       
        Set<Entry<String, Map<String, Integer>>> set1 =  datas.entrySet();    
        Iterator iterator1=(Iterator) set1.iterator();                       
        Iterator iterator2=null;                                                
        HashMap<String, Double> map =  null;
        Set<Entry<String,Double>> set2=null;
        Entry entry1=null;
        Entry entry2=null;
        
        while(iterator1.hasNext()){
            entry1=(Entry) iterator1.next();                                 
           
            map=(HashMap<String, Double>) entry1.getValue();
            set2=map.entrySet();                              
            iterator2=set2.iterator();                        
            while (iterator2.hasNext()) {
                entry2= (Entry) iterator2.next();
                ds.setValue(Double.parseDouble(entry2.getValue().toString()),
                               entry2.getKey().toString(),                         
                               entry1.getKey().toString());                    
                System.out.println("current:--- "+entry2.getKey().toString()+"--"
                              +entry2.getValue().toString()+"--"
                              +entry1.getKey().toString());
            }
            System.out.println("-------------------------------------");
        }               
    

        JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true, true, true);


        chart.getTitle().setFont(font);


        font = new Font("宋体", Font.BOLD, 15);
        chart.getLegend().setItemFont(font);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.getDomainAxis().setLabelFont(font);

        plot.getDomainAxis().setTickLabelFont(font);

        plot.getRangeAxis().setLabelFont(font);

        plot.setForegroundAlpha(1.0f);

        ChartUtilities.saveChartAsJPEG(new File("src/images/sales_of_dishes.jpg"), chart, 600, 400);
     }
    
    public void getSalesVolume() throws IOException, ParseException { 	
    	ListDAOImpl ldi = new ListDAOImpl();
        Map<String, Map<String, Integer>> datas = ldi.getSalesNum();
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        String timeInterval = getPastDate();
        ldi.createPort("Sales of Totoro Ramen from " + timeInterval.split(",")[0] + " to " + timeInterval.split(",")[1],datas,"Type","Sales Volume",font);
    }
    
}
