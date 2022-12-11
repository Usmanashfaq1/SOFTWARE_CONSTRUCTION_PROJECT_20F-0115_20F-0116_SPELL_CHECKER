package dataBaseLayer;

import java.util.ArrayList;

import tranferObjects.TransferObject;

public interface Facade {
	public boolean insertIntoDataBase(String title , String author , String paragraph);
	public boolean insertIntoDataBase(String word, int frequency);
	public boolean insertIntoDataBase(int mainId, int wordId);
	public void manipulateWord(int id , String updatedWord);
	public void manipulateWord(int id);
	public void txtFileGeneration();
	public ArrayList<String> maintainWordIDWithFile();
	public ArrayList<TransferObject> getWordForViewingInTable();
}
