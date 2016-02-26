package vn.toancauxanh.service.ws;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class JAXBConverter {

	public final static String NS = "http://schemas.datacontract.org/2004/07/GreenGlobal.Qlxdcb.Entities"; 
	
	public static JAXBElement<Integer> fromInteger(final String name, Integer value){
		return new JAXBElement<>(new QName(NS, name), Integer.class, value);
	}
	
	public static JAXBElement<Integer> fromInteger(final String name, int value){
		if(value==0)
		{
			return null;
		}
		return fromInteger(name,Integer.valueOf(value));
	}
	
	public static JAXBElement<Long> fromLong(final String name, long value){
		if(value==0)
		{
			return null;
		}
		return new JAXBElement<>(new QName(NS, name), Long.class, Long.valueOf(value));
	}
	
	public static JAXBElement<BigDecimal> fromBigDecimal(final String name, BigDecimal value){
		if(value==null|| value.doubleValue()==0)
		{

			return null;
		}
		return new JAXBElement<>(new QName(NS, name), BigDecimal.class, value);
	}
	
	public static JAXBElement<BigDecimal> fromDouble(final String name, Double value){
		BigDecimal temp = new BigDecimal(value);
		return new JAXBElement<>(new QName(NS, name), BigDecimal.class, temp);
	}
	
	public static JAXBElement<BigDecimal> fromDouble(final String name, double value){
		if(value==0)
		{
			return null;
		}
		return fromDouble(name,Double.valueOf(value));
	}
	
	public static JAXBElement<Boolean> fromBoolean(final String name, Boolean value){
		return new JAXBElement<>(new QName(NS, name), Boolean.class, value);
	}
	
	public static JAXBElement<String> fromString(final String name, String value){
		if(value==null|| value.isEmpty())
		{
			return null;
		}
		return new JAXBElement<>(new QName(NS, name), String.class, value);
	}
	
	public static JAXBElement<XMLGregorianCalendar> fromDate(final String name, Date value){
		if(value==null) return null;
		return new JAXBElement<>(new QName(NS, name), XMLGregorianCalendar.class, toXMLGregorianCalendar(value));
	}
	
	 /*
     * Converts JAXBElement<BigDecimal> to double in Java
     */
    public static long toLong(JAXBElement<Long> value){
        if(value == null || value.getValue()==null) {
            return 0;
        }
        return value.getValue().longValue();
    }
    
    public static int toInt(JAXBElement<Integer> value){
        if(value == null || value.getValue()==null) {
            return 0;
        }
        return value.getValue().intValue();
    }
    
    /*
     * Converts JAXBElement<BigDecimal> to double in Java
     */
    public static double toDouble(JAXBElement<BigDecimal> value){
        if(value == null || value.getValue()==null) {
            return 0;
        }
        return value.getValue().doubleValue();
    }
    
    public static String toString(JAXBElement<String> value){
        if(value == null) {
            return null;
        }
        return value.getValue();
    }
    
	/*
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
        return xmlCalendar;
    }
  
    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    public static Date toDate(XMLGregorianCalendar calendar){
    	System.out.println("calendar:"+calendar);
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }
    
   /* public static void main(String args[]) {
        Date today = new Date();
      
        //Converting date to XMLGregorianCalendar in Java
        XMLGregorianCalendar xml = toXMLGregorianCalendar(today);
        System.out.println("XMLGregorianCalendar from Date in Java      : " + xml) ;
      
        //Converting XMLGregorianCalendar to java.util.Date in Java
        Date date = toDate(xml);
        System.out.println("java.util.Date from XMLGregorianCalendar in Java : " + date);
      
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("after format : " + sdf.format(date));
     }*/

}
