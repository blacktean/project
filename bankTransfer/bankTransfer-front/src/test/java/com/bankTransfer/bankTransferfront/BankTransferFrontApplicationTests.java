/*
 * package com.bankTransfer.bankTransferfront;
 * 
 * import java.util.List;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.bankTransfer.pojo.Card; import
 * com.bankTransfer.service.ICardService; import
 * com.bankTransfer.util.UserContext;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest public class BankTransferFrontApplicationTests {
 * 
 * @Autowired private ICardService cardService;
 * 
 * @Test public void contextLoads() { List<Card> CardList1 =
 * cardService.queryCardByUserIdAndMajorCard("1", "0"); for (Card card :
 * CardList1) { System.err.println(card); }
 * 
 * List<Card> CardList2 = cardService.queryCardByUserIdAndMajorCard("1", "1");
 * for (Card card : CardList2) { System.err.println(card); } }
 * 
 * }
 */