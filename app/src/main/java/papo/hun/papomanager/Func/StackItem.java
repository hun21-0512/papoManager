package papo.hun.papomanager.Func;

public class StackItem {
   private int itemImg;
   private String title;
   private String contents;

   public StackItem(int itemImg, String title, String contents) {
      this.itemImg = itemImg;
      this.title = title;
      this.contents = contents;
   }

   public int getImg() {
      return itemImg;
   }

   public String getTitle() {
      return title;
   }

   public String getContents(){
      return contents;
   }
}