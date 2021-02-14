import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AllFilesInDisk {

	static SortedSet<String> allFileSet = new TreeSet<>();
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path configPath = FileSystems.getDefault().getPath("G://");
		/*
		 * List<Path> fileWithName =
		 * Files.walk(configPath).map(Path::getFileName)
		 * .sorted().collect(Collectors.toList()); for (Path name :
		 * fileWithName) { // printing the name of file in every sub folder
		 * System.out.println(name); }
		 */
		/*
		 * List<Path> list = Files.list(configPath).map(Path::getFileName).collect(Collectors.toList());
		list.removeIf(x -> x.toString().startsWith(".") || x.toString().startsWith("$"));
		for (Path p : list) {
			System.out.println(p + "\t" + Files.isDirectory(Paths.get(configPath.toString(), p.toString())));
		}
		*/
		
		String[] directories = {"E:\\", "F:\\", "G:\\"};
		for(String dir: directories){
			allFilesUnderDir(dir);
		}
		writeToFile();
		System.out.println(allFileSet.size());

	}
	
	private static void writeToFile() throws IOException{
		File notepad = new File("C:\\Users\\Home\\Documents\\FolderFile.txt");
		FileWriter fw = new FileWriter(notepad);
		BufferedWriter buff = new BufferedWriter(fw); 
		if(!notepad.exists()){
			notepad.createNewFile();
		}
		Iterator<String> iter = allFileSet.iterator();
		while(iter.hasNext()){
			buff.write(iter.next());
			buff.newLine();
		}
		buff.close();
	}

	private static void allFilesUnderDir(String string) {
		// TODO Auto-generated method stub
		File dir = new File(string);
		for (File f : dir.listFiles()) {
			if (f.isDirectory() && !(f.getName().startsWith("$") || f.getName().startsWith("."))) {
				try {
					allFileSet.add(f.getCanonicalPath().toString() + " *$* " + f.listFiles().length);
					allFilesUnderDir(f.getCanonicalPath().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (f.isFile() && !(f.getName().startsWith("$") || f.getName().startsWith("."))) {
				try {
					System.out.println(f.getCanonicalPath().toString());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
/*
 * File[] dirList = File.listRoots(); Set<String> extensions = new HashSet<>();
 * 
 * for (File allfiles : dirList) { File fileList[] = allfiles.listFiles(); for
 * (File files : fileList) { System.out.println(files.getAbsolutePath()); if
 * (files.isFile()) { String ext =
 * files.getName().substring(files.getName().indexOf('.')); extensions.add(ext);
 * 
 * } } }
 * 
 * Iterator<String> iter = extensions.iterator(); while (iter.hasNext()) {
 * System.out.println(iter.next()); }
 * 
 * // System.out.println(fileList.length);
 */
