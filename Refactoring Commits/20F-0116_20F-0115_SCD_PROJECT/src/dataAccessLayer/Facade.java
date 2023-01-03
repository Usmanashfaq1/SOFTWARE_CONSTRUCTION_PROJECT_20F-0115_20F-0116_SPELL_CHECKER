package dataAccessLayer;

import java.util.ArrayList;

import tranferObjects.TransferObject;

public interface Facade {
	public boolean insertIntoDataBase(String title , String author , String paragraph);
	public boolean insertIntoDataBase(String word, int frequency);
	public boolean insertIntoDataBase(int mainId, int wordId);
	public boolean manipulateWord(int id , String updatedWord);
	public boolean manipulateWord(int id);
	public boolean textFileGeneration();
	public ArrayList<String> maintainWordIDWithFile();
	public ArrayList<TransferObject> getWordForViewingInTable();
}
