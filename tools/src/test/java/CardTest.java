/**
 * @author caoqingyuan
 * @detail
 * @date 2022/5/19 15:30
 */
public class CardTest {
    public static void main(String[] args) {
        String card_no = CardAESUtil.getDecryptCardNo("917C52148E214E20B933E48606626B4D57B87B6752C2259C3AC83CE957046C8E", "&ajt3p7I");
        System.out.println("card_no="+card_no);
    }
}
