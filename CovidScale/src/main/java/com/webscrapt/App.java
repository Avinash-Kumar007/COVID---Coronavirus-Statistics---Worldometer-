package com.webscrapt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static String getData(String c) throws Exception {
        StringBuffer br = new StringBuffer();
        br.append( "<html>" + "<body style='text-align:center;color:green'>");
        br.append(c.toUpperCase()+"<br>");


        // url from main website of corona world meter
        String url="https://www.worldometers.info/coronavirus/country/"+ c +"/";
        Document doc = Jsoup.connect(url).get();

         System.out.println(doc.title());  //to print title of the body
//        System.out.println(doc.body().html());  to print complete html body


//#maincounter-wrap  (id name)
        Elements elements =doc.select("#maincounter-wrap");   //# used to select id wise
//        System.out.println(elements.html());        html details of above elements

        // details of all three elements
        elements.forEach((e)->{        // to set lemda expression press alt enter to set API


// loop will print data one by one for each element
//            System.out.println(e.html());
//            System.out.println();
//            System.out.println();


            String text= e.select("h1").text();
            String count= e.select(".maincounter-number>span").text();  // for class of div
//            System.out.println(text+" "+count);
            br.append(text).append("  ").append(count).append("<br>");
        });
        br.append("</body>" + "</html>");
     return br.toString();

    }
    public static void main( String[] args ) throws Exception
    {
//        System.out.println( "Hello World!" );
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter Your Country Name..");
//        String co= sc.nextLine();
//        System.out.println(getData(co));


        //creating gui
        JFrame root=new JFrame("Details of Country");
        root.setSize(500,500);

        Font f=new Font("poppins",Font.BOLD,30);


        //text Field
        JTextField field=new JTextField();

        //lable

        JLabel dataL= new JLabel();
        field.setFont(f);
        dataL.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);

//        button
        JButton button= new JButton("Get");
        button.addActionListener(event->{
            //click
        try {
            String maindata=field.getText();
            String result= getData(maindata);
            dataL.setText(result);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        });

        button.setFont(f);

        root.setLayout(new BorderLayout());

        root.add(field, BorderLayout.NORTH);
        root.add(dataL, BorderLayout.CENTER);
        root.add(button, BorderLayout.SOUTH);

        root.setVisible(true);

    }
}
