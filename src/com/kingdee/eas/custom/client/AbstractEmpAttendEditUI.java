/**
 * output package name
 */
package com.kingdee.eas.custom.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractEmpAttendEditUI extends com.kingdee.eas.framework.client.EditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractEmpAttendEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contempNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contempName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contperiod;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbingjia;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contshijia;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contchanjia;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkuanggong;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbingjiakoukuan;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkaoqinkoukuan;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox txtName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtSimpleName;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox txtDescription;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtempNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtempName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtperiod;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtbingjia;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtshijia;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtchanjia;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtkuanggong;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtbingjiakoukuan;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtkaoqinkoukuan;
    protected com.kingdee.eas.custom.EmpAttendInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractEmpAttendEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractEmpAttendEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contempNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contempName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contperiod = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbingjia = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contshijia = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contchanjia = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contkuanggong = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbingjiakoukuan = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contkaoqinkoukuan = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtName = new com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox();
        this.txtSimpleName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtDescription = new com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox();
        this.txtempNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtempName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtperiod = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbingjia = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtshijia = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtchanjia = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtkuanggong = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtbingjiakoukuan = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtkaoqinkoukuan = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.contempNumber.setName("contempNumber");
        this.contempName.setName("contempName");
        this.contperiod.setName("contperiod");
        this.contbingjia.setName("contbingjia");
        this.contshijia.setName("contshijia");
        this.contchanjia.setName("contchanjia");
        this.contkuanggong.setName("contkuanggong");
        this.contbingjiakoukuan.setName("contbingjiakoukuan");
        this.contkaoqinkoukuan.setName("contkaoqinkoukuan");
        this.txtNumber.setName("txtNumber");
        this.txtName.setName("txtName");
        this.txtSimpleName.setName("txtSimpleName");
        this.txtDescription.setName("txtDescription");
        this.txtempNumber.setName("txtempNumber");
        this.txtempName.setName("txtempName");
        this.txtperiod.setName("txtperiod");
        this.txtbingjia.setName("txtbingjia");
        this.txtshijia.setName("txtshijia");
        this.txtchanjia.setName("txtchanjia");
        this.txtkuanggong.setName("txtkuanggong");
        this.txtbingjiakoukuan.setName("txtbingjiakoukuan");
        this.txtkaoqinkoukuan.setName("txtkaoqinkoukuan");
        // CoreUI		
        this.btnPrint.setVisible(false);		
        this.btnPrintPreview.setVisible(false);		
        this.menuItemPrint.setVisible(false);		
        this.menuItemPrintPreview.setVisible(false);
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);		
        this.kDLabelContainer4.setBoundLabelAlignment(7);		
        this.kDLabelContainer4.setVisible(true);
        // contempNumber		
        this.contempNumber.setBoundLabelText(resHelper.getString("contempNumber.boundLabelText"));		
        this.contempNumber.setBoundLabelLength(100);		
        this.contempNumber.setBoundLabelUnderline(true);		
        this.contempNumber.setVisible(true);
        // contempName		
        this.contempName.setBoundLabelText(resHelper.getString("contempName.boundLabelText"));		
        this.contempName.setBoundLabelLength(100);		
        this.contempName.setBoundLabelUnderline(true);		
        this.contempName.setVisible(true);
        // contperiod		
        this.contperiod.setBoundLabelText(resHelper.getString("contperiod.boundLabelText"));		
        this.contperiod.setBoundLabelLength(100);		
        this.contperiod.setBoundLabelUnderline(true);		
        this.contperiod.setVisible(true);
        // contbingjia		
        this.contbingjia.setBoundLabelText(resHelper.getString("contbingjia.boundLabelText"));		
        this.contbingjia.setBoundLabelLength(100);		
        this.contbingjia.setBoundLabelUnderline(true);		
        this.contbingjia.setVisible(true);
        // contshijia		
        this.contshijia.setBoundLabelText(resHelper.getString("contshijia.boundLabelText"));		
        this.contshijia.setBoundLabelLength(100);		
        this.contshijia.setBoundLabelUnderline(true);		
        this.contshijia.setVisible(true);
        // contchanjia		
        this.contchanjia.setBoundLabelText(resHelper.getString("contchanjia.boundLabelText"));		
        this.contchanjia.setBoundLabelLength(100);		
        this.contchanjia.setBoundLabelUnderline(true);		
        this.contchanjia.setVisible(true);
        // contkuanggong		
        this.contkuanggong.setBoundLabelText(resHelper.getString("contkuanggong.boundLabelText"));		
        this.contkuanggong.setBoundLabelLength(100);		
        this.contkuanggong.setBoundLabelUnderline(true);		
        this.contkuanggong.setVisible(true);
        // contbingjiakoukuan		
        this.contbingjiakoukuan.setBoundLabelText(resHelper.getString("contbingjiakoukuan.boundLabelText"));		
        this.contbingjiakoukuan.setBoundLabelLength(100);		
        this.contbingjiakoukuan.setBoundLabelUnderline(true);		
        this.contbingjiakoukuan.setVisible(true);
        // contkaoqinkoukuan		
        this.contkaoqinkoukuan.setBoundLabelText(resHelper.getString("contkaoqinkoukuan.boundLabelText"));		
        this.contkaoqinkoukuan.setBoundLabelLength(100);		
        this.contkaoqinkoukuan.setBoundLabelUnderline(true);		
        this.contkaoqinkoukuan.setVisible(true);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // txtName
        // txtSimpleName		
        this.txtSimpleName.setMaxLength(80);
        // txtDescription
        // txtempNumber		
        this.txtempNumber.setVisible(true);		
        this.txtempNumber.setHorizontalAlignment(2);		
        this.txtempNumber.setMaxLength(100);		
        this.txtempNumber.setRequired(false);
        // txtempName		
        this.txtempName.setVisible(true);		
        this.txtempName.setHorizontalAlignment(2);		
        this.txtempName.setMaxLength(100);		
        this.txtempName.setRequired(false);
        // txtperiod		
        this.txtperiod.setVisible(true);		
        this.txtperiod.setHorizontalAlignment(2);		
        this.txtperiod.setMaxLength(100);		
        this.txtperiod.setRequired(false);
        // txtbingjia		
        this.txtbingjia.setVisible(true);		
        this.txtbingjia.setHorizontalAlignment(2);		
        this.txtbingjia.setDataType(1);		
        this.txtbingjia.setSupportedEmpty(true);		
        this.txtbingjia.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtbingjia.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtbingjia.setPrecision(10);		
        this.txtbingjia.setRequired(false);
        // txtshijia		
        this.txtshijia.setVisible(true);		
        this.txtshijia.setHorizontalAlignment(2);		
        this.txtshijia.setDataType(1);		
        this.txtshijia.setSupportedEmpty(true);		
        this.txtshijia.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtshijia.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtshijia.setPrecision(10);		
        this.txtshijia.setRequired(false);
        // txtchanjia		
        this.txtchanjia.setVisible(true);		
        this.txtchanjia.setHorizontalAlignment(2);		
        this.txtchanjia.setDataType(1);		
        this.txtchanjia.setSupportedEmpty(true);		
        this.txtchanjia.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtchanjia.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtchanjia.setPrecision(10);		
        this.txtchanjia.setRequired(false);
        // txtkuanggong		
        this.txtkuanggong.setVisible(true);		
        this.txtkuanggong.setHorizontalAlignment(2);		
        this.txtkuanggong.setDataType(1);		
        this.txtkuanggong.setSupportedEmpty(true);		
        this.txtkuanggong.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtkuanggong.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtkuanggong.setPrecision(10);		
        this.txtkuanggong.setRequired(false);
        // txtbingjiakoukuan		
        this.txtbingjiakoukuan.setVisible(true);		
        this.txtbingjiakoukuan.setHorizontalAlignment(2);		
        this.txtbingjiakoukuan.setDataType(1);		
        this.txtbingjiakoukuan.setSupportedEmpty(true);		
        this.txtbingjiakoukuan.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtbingjiakoukuan.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtbingjiakoukuan.setPrecision(10);		
        this.txtbingjiakoukuan.setRequired(false);
        // txtkaoqinkoukuan		
        this.txtkaoqinkoukuan.setVisible(true);		
        this.txtkaoqinkoukuan.setHorizontalAlignment(2);		
        this.txtkaoqinkoukuan.setDataType(1);		
        this.txtkaoqinkoukuan.setSupportedEmpty(true);		
        this.txtkaoqinkoukuan.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtkaoqinkoukuan.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtkaoqinkoukuan.setPrecision(10);		
        this.txtkaoqinkoukuan.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtempNumber,txtempName,txtperiod,txtbingjia,txtshijia,txtchanjia,txtkuanggong,txtbingjiakoukuan,txtkaoqinkoukuan}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 1013, 149));
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(10, 82, 270, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(341, 82, 270, 19));
        this.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(672, 82, 270, 19));
        this.add(kDLabelContainer3, null);
        kDLabelContainer4.setBounds(new Rectangle(10, 106, 270, 19));
        this.add(kDLabelContainer4, null);
        contempNumber.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contempNumber, null);
        contempName.setBounds(new Rectangle(341, 10, 270, 19));
        this.add(contempName, null);
        contperiod.setBounds(new Rectangle(672, 10, 270, 19));
        this.add(contperiod, null);
        contbingjia.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contbingjia, null);
        contshijia.setBounds(new Rectangle(341, 34, 270, 19));
        this.add(contshijia, null);
        contchanjia.setBounds(new Rectangle(672, 34, 270, 19));
        this.add(contchanjia, null);
        contkuanggong.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contkuanggong, null);
        contbingjiakoukuan.setBounds(new Rectangle(341, 58, 270, 19));
        this.add(contbingjiakoukuan, null);
        contkaoqinkoukuan.setBounds(new Rectangle(672, 58, 270, 19));
        this.add(contkaoqinkoukuan, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtNumber);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(txtName);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtSimpleName);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(txtDescription);
        //contempNumber
        contempNumber.setBoundEditor(txtempNumber);
        //contempName
        contempName.setBoundEditor(txtempName);
        //contperiod
        contperiod.setBoundEditor(txtperiod);
        //contbingjia
        contbingjia.setBoundEditor(txtbingjia);
        //contshijia
        contshijia.setBoundEditor(txtshijia);
        //contchanjia
        contchanjia.setBoundEditor(txtchanjia);
        //contkuanggong
        contkuanggong.setBoundEditor(txtkuanggong);
        //contbingjiakoukuan
        contbingjiakoukuan.setBoundEditor(txtbingjiakoukuan);
        //contkaoqinkoukuan
        contkaoqinkoukuan.setBoundEditor(txtkaoqinkoukuan);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnReset);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("name", String.class, this.txtName, "_multiLangItem");
		dataBinder.registerBinding("simpleName", String.class, this.txtSimpleName, "text");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "_multiLangItem");
		dataBinder.registerBinding("empNumber", String.class, this.txtempNumber, "text");
		dataBinder.registerBinding("empName", String.class, this.txtempName, "text");
		dataBinder.registerBinding("period", String.class, this.txtperiod, "text");
		dataBinder.registerBinding("bingjia", java.math.BigDecimal.class, this.txtbingjia, "value");
		dataBinder.registerBinding("shijia", java.math.BigDecimal.class, this.txtshijia, "value");
		dataBinder.registerBinding("chanjia", java.math.BigDecimal.class, this.txtchanjia, "value");
		dataBinder.registerBinding("kuanggong", java.math.BigDecimal.class, this.txtkuanggong, "value");
		dataBinder.registerBinding("bingjiakoukuan", java.math.BigDecimal.class, this.txtbingjiakoukuan, "value");
		dataBinder.registerBinding("kaoqinkoukuan", java.math.BigDecimal.class, this.txtkaoqinkoukuan, "value");		
	}
	//Regiester UI State
	private void registerUIState(){
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtDescription, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtNumber, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtSimpleName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtDescription, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtNumber, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtSimpleName, ActionStateConst.ENABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtName, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtDescription, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtNumber, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtSimpleName, ActionStateConst.DISABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.app.EmpAttendEditUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }


    /**
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.txtempNumber.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.EmpAttendInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"NONE",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("NONE");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
		}

    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("simpleName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("empNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("empName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("period", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bingjia", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("shijia", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("chanjia", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("kuanggong", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bingjiakoukuan", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("kaoqinkoukuan", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
		            this.txtName.setEnabled(true);
		            this.txtDescription.setEnabled(true);
		            this.txtNumber.setEnabled(true);
		            this.txtSimpleName.setEnabled(true);
        } else if (STATUS_EDIT.equals(this.oprtState)) {
		            this.txtName.setEnabled(true);
		            this.txtDescription.setEnabled(true);
		            this.txtNumber.setEnabled(true);
		            this.txtSimpleName.setEnabled(true);
        } else if (STATUS_VIEW.equals(this.oprtState)) {
		            this.txtName.setEnabled(false);
		            this.txtDescription.setEnabled(false);
		            this.txtNumber.setEnabled(false);
		            this.txtSimpleName.setEnabled(false);
        }
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("simpleName"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("empNumber"));
        sic.add(new SelectorItemInfo("empName"));
        sic.add(new SelectorItemInfo("period"));
        sic.add(new SelectorItemInfo("bingjia"));
        sic.add(new SelectorItemInfo("shijia"));
        sic.add(new SelectorItemInfo("chanjia"));
        sic.add(new SelectorItemInfo("kuanggong"));
        sic.add(new SelectorItemInfo("bingjiakoukuan"));
        sic.add(new SelectorItemInfo("kaoqinkoukuan"));
        return sic;
    }        

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.client", "EmpAttendEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.custom.client.EmpAttendEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.EmpAttendFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.EmpAttendInfo objectValue = new com.kingdee.eas.custom.EmpAttendInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }



    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}