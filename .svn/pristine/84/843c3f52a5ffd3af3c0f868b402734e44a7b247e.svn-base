package ftp.util;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class CommonUtils {

	public static String timeConvertLong2String(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.getTime().toLocaleString();
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	} 
	
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean deleteF(String path){
    	File f = new File(path);
    	if(f.isFile()){
    		return deleteFile(path);
    	} else if(f.isDirectory()){
    		return deleteDirectory(path);
    	}
    	return false;
    }
    
    public static boolean rename(String path, String newName){
    	File file = new File(path);
    	String newPath = file.getParentFile().getPath() + "\\" + newName;
    	if(newPath.equals(path)) {
    		return true;
    	}
    	File newFile = new File(newPath);
    	if(newFile.exists()){
    		return false;
    	}else {
    		return file.renameTo(newFile);
    	}
    }

	@Test
	public void test(){
		Integer[] nums = new Integer[]{4,5,6};
		List<Integer> l = Arrays.asList(nums);
		//List<Integer> l = new ArrayList<>();l.add(4);l.add(5);l.add(6);
		for(Iterator<Integer> it = l.iterator(); it.hasNext();){
			it.next();
			it.remove();
		}
		System.out.println(l.size());
	}
	@Test
	public void testDeleteDir(){
		deleteDirectory("C:\\Users\\lixiaohui\\Desktop\\昆虫与人类 - 副本");
	}
	
	@Test
	public void testRename(){
		rename("C:\\Users\\lixiaohui\\Desktop\\昆虫与人类 1\\作业.docx","作业r.docx");
	}
}
