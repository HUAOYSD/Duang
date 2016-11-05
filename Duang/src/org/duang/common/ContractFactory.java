package org.duang.common;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.duang.entity.InvestList;
import org.duang.entity.MemberInfo;
import org.duang.util.DataUtils;
import org.duang.util.DateUtils;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class ContractFactory extends Thread{
	private MemberInfo investMember;
	private List<Map<String, String>> loanMembers;
	private InvestList investList;
	private String contractNo;
	private String fullPath;
	
	private ContractFactory(){
	}
	private static ContractFactory instance;  
	public static ContractFactory getInstance(MemberInfo investMember, List<Map<String, String>> loanMembers, InvestList investList, String contractNo, String fullPath) throws Exception{
		instance = new ContractFactory();  
		instance.setInvestMember(investMember);
		instance.setLoanMembers(loanMembers);
		instance.setInvestList(investList);
		instance.setContractNo(contractNo);
		instance.setFullPath(fullPath);
		return instance;
	}
	/**
	 * 执行创建合同
	 * <p>Title: run</p>   
	 * <p>Description: </p>  
	 * @author LiYonghui
	 * @date 2016年10月28日 下午5:19:09   
	 * @see java.lang.Thread#run()
	 */
	public void run() {  
		try {
			synchronized (this) {
				createContract();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**   
	 * 创建合同
	 * @Title: createContract   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @throws Exception  
	 * @author 5y    
	 * @date 2016年9月18日 下午3:25:52
	 * @return: void      
	 * @throws   
	 */  
	public synchronized void createContract() throws Exception{
		//
		Document document = new Document(PageSize.A4, 60, 60, 20, 0); 
		document.addTitle("借贷合同");
		document.addSubject("服务协议");
		document.addAuthor("Administrator");
		document.addCreator("Administrator");
		document.addCreationDate();
		//
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font boldFont = new Font(bfChinese, 18, Font.BOLD);
		Font boldFont_Small = new Font(bfChinese, 7, Font.BOLD);
		Font normalFont = new Font(bfChinese, 7, Font.NORMAL);
		//
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(fullPath+"\\"+contractNo+".pdf")));
		writer.setViewerPreferences(PdfWriter.HideMenubar);
		//
		document.open();
		/**
		 * Chunk文本快
		 * Phrase短语
		 * Paragraph段落
		 * Table表格
		 * Graphic图标
		 */
		//Paragraph————标题
		Chunk chunk = new Chunk("服务协议", boldFont);
		Paragraph paragraph = new Paragraph(chunk);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);

		//Paragraph————合同编号
		paragraph = new Paragraph("合同编号： "+contractNo , normalFont);
		paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
		//Graphic————章印
		//Image jpeg = Image.getInstance("D:\\zhang.jpg");
		//jpeg.setAlignment(Image.RIGHT | Image.TEXTWRAP);
		//jpeg.scaleAbsolute(60, 60);
		//document.add(jpeg);
		document.add(paragraph);

		//Paragraph————甲方
		paragraph = new Paragraph("甲方(出借人)："+investMember.getRealName(), normalFont);
		paragraph.setSpacingBefore(20);
		document.add(paragraph);
		paragraph = new Paragraph("身份证号码： "+investMember.getIdCard(), normalFont);
		document.add(paragraph);
		//Paragraph————乙方
		paragraph = new Paragraph("乙方： 北京华澳融信国际投资管理咨询有限公司", normalFont);
		paragraph.setSpacingBefore(20);
		document.add(paragraph);
		paragraph = new Paragraph("通信地址： 北京市朝阳区永安东里甲3号院1号楼20层2307内2307B", normalFont);
		document.add(paragraph);
		//Paragraph————丙方
		paragraph = new Paragraph("丙方(余盆平台的运营管理人)：北京华澳翼时代信息技术有限责任公司", normalFont);
		paragraph.setSpacingBefore(20);
		document.add(paragraph);
		paragraph = new Paragraph("通信地址：北京市朝阳区望京东园七区19号保利国际广场T1-803室", normalFont);
		document.add(paragraph);
		//Paragraph————规定
		Phrase phrase = new Phrase();
		phrase.add(new Chunk("          根据《中华人民共和国合同法》及《电子签名法》等相关法律法规的规定，", normalFont));
		phrase.add(new Chunk("就乙方为甲方提供出借咨询、出借促成、财富规划、投资管理等服务；", normalFont));
		phrase.add(new Chunk("丙方(余盆平台(余盆金融网平台)的运营管理人)为甲方提供借款人推荐、风险管理、贷后管理服务及金融信息和相关服务事宜。", normalFont));
		phrase.add(new Chunk("三方遵守平等、自愿、互利和诚实信用原则，达成本《服务协议》，以兹信守。", normalFont));
		paragraph = new Paragraph(phrase);
		paragraph.setSpacingBefore(20);
		document.add(paragraph);
		//Table————详细
		Table table = new Table(2, 7);
		table.setBorderWidth(1); 
		table.setBorderColor(new Color(39, 47, 40)); 
		table.setPadding(4); 
		table.setSpacing(0); 
		table.setWidths(new float[]{4f, 6f});
		table.setWidth(100);
		Cell cell = new Cell(new Paragraph("借款协议编号：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("DDH-0ccdd005-7c21-454c-99d6-4e5562ad9cef", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("借款人：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("详见附件借款人列表", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("出借人：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph(investMember.getRealName(), normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("出借金额：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph(investList.getMoney()+"", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("出借期限：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph(investList.getDays()+"", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("出借日期：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph(DateUtils.date2Str(investList.getCalcBeginDate(), "yyyy-MM-dd"), normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("预期年化收益率：", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph(investList.getScale().getRevenue()+"", normalFont));
		table.addCell(cell);
		document.add(table);
		//Paragraph————释义
		paragraph = new Paragraph("第一条 释义", boldFont_Small);
		paragraph.setSpacingBefore(15);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("在本协议中，除非上下文另有解释，下列词语具有以下含义：", normalFont));
		document.add(new Paragraph("          1.1 借款人：指有资金需求、经丙方筛选推荐给出借人并得到出借人一定数量的出借资金的自然人。", normalFont));
		document.add(new Paragraph("          1.2 出借人：指自主选择出借一定数量资金给他人的自然人。", normalFont));
		document.add(new Paragraph("          1.3 还款风险金：指为乙方所服务的出借人的共同利益考虑、由丙方出资设立并管理的专用资金，款项专款专用，用于补偿乙方所服务的出借人一定程度的潜在回款损失。", normalFont));
		document.add(new Paragraph("          1.4 债权：指出借人与借款人借贷关系存续期间出借人拥有的全部权益，债权以人民币计价。", normalFont));
		document.add(new Paragraph("          1.5 转让权：指出借人在约定出借期届满后,全部或部分转让债权的权益。", normalFont));
		document.add(new Paragraph("          1.6 工作日：指中华人民共和国法律规定的工作日（法定工作日）。", normalFont));
		document.add(new Paragraph("          1.7 提前还款：指《借款协议》中约定了借款人的本息偿还周期和金额等相关还款计划，借款人可能在协议规定的偿还周期结束前，在某一期将剩余本金提前偿还给出借人，从而使出借人的资金比约定的计划提前收回。", normalFont));
		//Paragraph————甲方的资金出借方式
		paragraph = new Paragraph("第二条 甲方的资金出借方式", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          2.1 甲方通过丙方对借款人的信用评估及筛选，通过直接出借或债权受让的方式与丙方推荐的借款人建立借贷关系。自甲方点击\"确认购买\"并随后显示\"恭喜购买成功\"标示后，甲方与借款人的借贷关系建立，并从成功出借当天开始计息。", normalFont));
		document.add(new Paragraph("          2.2 甲方与借款人建立借贷关系时，甲方同意并指定甲方委托的第三方，通过托管的金账户将出借资金划转给借款人和/或债权的转让方，并同时就所确立债权的后续服务，委托丙方对债权投资管理、资金回收、债权实现等提供风险控制服务。", normalFont));
		document.add(new Paragraph("          2.3 甲方出借一笔资金后，在12个月内，甲方委托乙方按月代划转的借款人偿还的本息，通过乙方推荐进行新一次的出借，款项由乙方代为划转给所受让的债权的转让方。", normalFont));
		document.add(new Paragraph("          2.4 甲方在该笔资金出借满3个月后至12个月（含12月）期间，甲方能行使转让权。甲方可根据其使用情况自行提出申请，对该笔资金对应的债权资产（包括收益部分）全部转让给乙方为其推荐的债权受让人。转让对价分别约为：满3个月后约为甲方初始出借资金金额的101.8%；满6个月后约为甲方初始出借资金金额的104.25%；满9个月后约为甲方初始出借资金金额的107.5%；满12个月后约为甲方初始出借资金金额的112%；由债权受让人在甲方提出申请后的5个工作日内并且出借满3个月后支付到甲方托管的金账户中，供甲方自行支配。", normalFont));
		document.add(new Paragraph("          2.5 乙方为甲方成功推荐债权受让人，甲方接受并由乙方协助进行债权转让〔转让对价为该笔债权当时的应有价值〕。", normalFont));
		document.add(new Paragraph("          2.6 债权转让申请日：甲方可在该笔资金出借满3个月前5个工作日以后至出借满12个月之前向乙方提出债权转让申请，经乙方按照相对应对价为甲方推荐债权受让人，若未在规定时间内提出债权转让申请，则甲方该笔出借资金自动进入下一个出借周期。", normalFont));
		//Paragraph————第三条 借款人回款风险的处置方式
		paragraph = new Paragraph("第三条 借款人回款风险的处置方式", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          3.1 当借款人发生违约时，还款风险金进行借款人回款风险的补偿，规则如下：还款风险金的提取比例由丙方根据借款人的整体违约状况进行设定，并有权进行适当的调整。丙方公示还款风险金信息情况；同时，如果甲方得到还款风险金的补偿，丙方有权对外披露甲方的具体受偿情况；如丙方需要，甲方应协助乙方进行由于借款人违约行为而产生的相关法律诉讼行为。", normalFont));
		document.add(new Paragraph("          3.2 由还款风险金进行风险补偿的处置方式，甲方受偿可能属于如下情形之一：", normalFont));
		document.add(new Paragraph("                    3.2.1 在还款风险金当期余额足以支付当期（每月为一期）所有选择此方式的出借人所对应的发生逾期的借款人的逾期本息时，由还款风险金将当期所有违约借款人的全部逾期本息金额支付给甲方及其他出借人，甲方和其他出借人在各自与借款人借贷关系存续期间约定的本息回收情况将保持不变。在甲方得到还款风险金代偿当期本息后，当期本息债权归丙方所有，借款人其后所偿还的逾期本息归属丙方的还款风险金，同时逾期罚息、违约金等相关权益将作为甲方支付给丙方的催收服务费。", normalFont));
		document.add(new Paragraph("                    3.2.2 在还款风险金当期余额不足以支付当期（每月为一期）所有选择此方式的出借人所对应的发生逾期的借款人的逾期本息时，则当期所有选择此方式的出借人按照各自对应的违约借款人的逾期本息金额占当期所有出借人对应的违约借款人的逾期本息总额的比例对还款风险金专用账户的当期资金进行分配，甲方和其他出借人当期未得到分配的部分自动记入下一期，与下一期发生的新逾期款继续进行上述同样原则的按比例分配，依此类推，直至完全清偿出借人的本息。", normalFont));
		//Paragraph————第四条 甲方的债权受让及转让
		paragraph = new Paragraph("第四条 甲方的债权受让及转让", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          4.1 倘若乙方和丙方为甲方成功推荐借款人，甲方和借款人建立借贷关系，若出借资金产生剩余利息部分的，将作为甲方支付给乙方和丙方的咨询服务费。", normalFont));
		document.add(new Paragraph("          4.2 在初始约定甲方的出借期届满前，若有提前还款等情况，甲方同意与丙方推荐的其他借款人建立借贷关系，而无需另行签订相关协议，丙方有权将提前还款的款项出借给其他借款人，使甲方的出借金额维持本协议约定的出借金额，直至初始约定出借期届满。", normalFont));
		document.add(new Paragraph("          4.3 在初始约定甲方的出借期届满后，丙方将在5个工作日内将到期债权的本金及利息返还到甲方托管的金账户中，供甲方自行支配。", normalFont));
		document.add(new Paragraph("          4.4 在初始约定甲方的出借期届满后，若有未到期或延期债权，丙方将在5个工作日内协助转让甲方权益下的所有债权，转让成功后将本金及利息返还到甲方托管的金账户中，供甲方自行支配。", normalFont));
		//Paragraph————第五条 风险提示
		paragraph = new Paragraph("第五条 风险提示", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("5.1 政策风险", normalFont));
		document.add(new Paragraph("          国家因宏观政策、财政政策、货币政策、行业政策、地区发展政策等因素引起的系统风险；", normalFont));
		document.add(new Paragraph("5.2 借款人信用风险", normalFont));
		document.add(new Paragraph("          当借款人短期或者长期丧失还款能力（包括但不限于借款人收入情况，财产状况发生变化，人身出现意外、发生疾病、死亡等情况），或者借款人的还款意愿发生变化，同时当还款风险金余额不足以弥补当期所有的逾期借款人的违约金额时，甲方当期应得到的回款可能延迟回收；", normalFont));
		document.add(new Paragraph("5.3 资金流动性风险", normalFont));
		document.add(new Paragraph("          在出借期限内,除非甲方有严重违约的情形导致甲方合同根本目的无法实现,否则,甲方不能单方解除本协议。因此，甲方在出借期限届满前，存在无法收回出借金额本金及利息的风险。", normalFont));
		document.add(new Paragraph("5.4 不可抗力", normalFont));
		document.add(new Paragraph("          由于战争、动乱、自然灾害等不可抗力因素的出现而可能导致甲方资产损失的风险。", normalFont));
		//Paragraph————第六条 税务处理
		paragraph = new Paragraph("第六条 税务处理", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          甲方在资金出借、转让过程产生的相关税费，由甲方自行向其主管税务机关申报、缴纳、乙方、丙方不负责相关事宜处理。", normalFont));
		//Paragraph————第七条 违约责任
		paragraph = new Paragraph("第七条 违约责任", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          任何一方违反本协议的约定，使得本协议的全部或部分不能履行，均应承担违约责任，并赔偿对方因此遭受的损失（包括由此产生的诉讼费和律师费）；如多方违约，根据实际情况各自承担相应的责任。违约方应赔偿因其违约而给守约方造成的损失，包括合同履行后可以获得的利益，但不得超过违反合同一方订立合同时可以预见或应当预见的因违反合同可能造成的损失。", normalFont));
		//Paragraph————第八条 争议的处理
		paragraph = new Paragraph("第八条 争议的处理", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          本协议在履行过程中，如发生任何争执或纠纷，且协商不成的，约定向 合同签订地 人民法院提起诉讼。", normalFont));
		//Paragraph————第九条 其他事项
		paragraph = new Paragraph("第九条 其他事项", boldFont_Small);
		paragraph.setSpacingBefore(8);
		paragraph.setSpacingAfter(8);
		document.add(paragraph);
		document.add(new Paragraph("          9.1 如果甲方出现出借资产的继承或赠与，必须由主张权利的继承人或受赠人向丙方出示经国家有权机关认证的继承或赠与权利归属证明文件，丙方确认后方予协助进行资产的转移，由此产生的相关税费，由主张权利的继承人或受赠人，向其主管税务机关申报、缴纳，乙方、丙方不负责相关事宜处理；", normalFont));
		document.add(new Paragraph("          9.2 本协议附件作为本协议的有效组成部分，与本协议效力一致：本协议附件与本协议的规定不一致的，以附件为准；", normalFont));
		document.add(new Paragraph("          9.3 三方确认，本协议的签署，生效和履行以不违反中国的法律法规为前提。如果本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力；", normalFont));
		document.add(new Paragraph("          9.4 本协议由三方在四川省成都市武侯区签订并履行。", normalFont));

		//Paragraph————借款人列表
		chunk = new Chunk("借款人列表", boldFont);
		paragraph = new Paragraph(chunk);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.setSpacingBefore(20);
		paragraph.setSpacingAfter(15);
		document.add(paragraph);
		//Paragraph————合同编号
		paragraph = new Paragraph("合同编号： " + contractNo, normalFont);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);
		//Paragraph————甲方（借款人）
		paragraph = new Paragraph("甲方（借款人）：", normalFont);
		paragraph.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(paragraph);
		//Table————借款人列表
		table = new Table(3, loanMembers == null ? 0 : loanMembers.size());
		table.setBorderWidth(1); 
		table.setBorderColor(new Color(39, 47, 40)); 
		table.setPadding(4); 
		table.setSpacing(0); 
		table.setWidths(new float[]{3f, 5f, 2f});
		table.setWidth(100);
		cell = new Cell(new Paragraph("姓名", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("身份证号码", normalFont));
		table.addCell(cell);
		cell = new Cell(new Paragraph("金额/元", normalFont));
		table.addCell(cell);
		if (DataUtils.notEmpty(loanMembers)) {
			for (Map<String, String> map : loanMembers) {
				cell = new Cell(new Paragraph(map.get("name"), normalFont));
				table.addCell(cell);
				cell = new Cell(new Paragraph(map.get("idcard"), normalFont));
				table.addCell(cell);
				cell = new Cell(new Paragraph(map.get("money"), normalFont));
				table.addCell(cell);
			}
		}
		document.add(table);
		document.close();
	}
	public MemberInfo getInvestMember() {
		return investMember;
	}
	public void setInvestMember(MemberInfo investMember) {
		this.investMember = investMember;
	}
	public List<Map<String, String>> getLoanMembers() {
		return loanMembers;
	}
	public void setLoanMembers(List<Map<String, String>> loanMembers) {
		this.loanMembers = loanMembers;
	}
	public InvestList getInvestList() {
		return investList;
	}
	public void setInvestList(InvestList investList) {
		this.investList = investList;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
}