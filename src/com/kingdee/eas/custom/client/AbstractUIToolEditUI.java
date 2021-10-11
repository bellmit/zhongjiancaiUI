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
public abstract class AbstractUIToolEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractUIToolEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contauditTitle;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbostype;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conturl;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttableName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contfileViewNode;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contfileUpNode;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtauditTitle;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtbostype;
    protected com.kingdee.bos.ctrl.swing.KDTextField txturl;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel2;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntrys;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntrys_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtTabEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtTabEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtTabTableEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtTabTableEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttableName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtfileViewNode;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtfileUpNode;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnEditBill;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddTab;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRemoveTab;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddLineRow;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRemoveLineRow;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRowUp;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRowDown;
    protected com.kingdee.eas.custom.UIToolInfo editData = null;
    protected ActionEditBill actionEditBill = null;
    protected ActionAddTab actionAddTab = null;
    protected ActionRemoveTab actionRemoveTab = null;
    protected ActionAddLineRow actionAddLineRow = null;
    protected ActionRemoveLineRow actionRemoveLineRow = null;
    protected ActionrRowUp actionRowUp = null;
    protected ActionRowDown actionRowDown = null;
    /**
     * output class constructor
     */
    public AbstractUIToolEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractUIToolEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionEditBill
        this.actionEditBill = new ActionEditBill(this);
        getActionManager().registerAction("actionEditBill", actionEditBill);
         this.actionEditBill.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddTab
        this.actionAddTab = new ActionAddTab(this);
        getActionManager().registerAction("actionAddTab", actionAddTab);
         this.actionAddTab.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRemoveTab
        this.actionRemoveTab = new ActionRemoveTab(this);
        getActionManager().registerAction("actionRemoveTab", actionRemoveTab);
         this.actionRemoveTab.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddLineRow
        this.actionAddLineRow = new ActionAddLineRow(this);
        getActionManager().registerAction("actionAddLineRow", actionAddLineRow);
         this.actionAddLineRow.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRemoveLineRow
        this.actionRemoveLineRow = new ActionRemoveLineRow(this);
        getActionManager().registerAction("actionRemoveLineRow", actionRemoveLineRow);
         this.actionRemoveLineRow.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRowUp
        this.actionRowUp = new ActionrRowUp(this);
        getActionManager().registerAction("actionRowUp", actionRowUp);
         this.actionRowUp.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRowDown
        this.actionRowDown = new ActionRowDown(this);
        getActionManager().registerAction("actionRowDown", actionRowDown);
         this.actionRowDown.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contauditTitle = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbostype = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conturl = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.conttableName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contfileViewNode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contfileUpNode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtauditTitle = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbostype = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txturl = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel2 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtEntrys = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtTabEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtTabTableEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txttableName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtfileViewNode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtfileUpNode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.btnEditBill = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddTab = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRemoveTab = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddLineRow = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRemoveLineRow = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRowUp = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRowDown = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contAuditor.setName("contAuditor");
        this.contauditTitle.setName("contauditTitle");
        this.contbostype.setName("contbostype");
        this.conturl.setName("conturl");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.conttableName.setName("conttableName");
        this.contfileViewNode.setName("contfileViewNode");
        this.contfileUpNode.setName("contfileUpNode");
        this.prmtCreator.setName("prmtCreator");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtAuditor.setName("prmtAuditor");
        this.txtauditTitle.setName("txtauditTitle");
        this.txtbostype.setName("txtbostype");
        this.txturl.setName("txturl");
        this.kDPanel1.setName("kDPanel1");
        this.kDPanel2.setName("kDPanel2");
        this.kdtEntrys.setName("kdtEntrys");
        this.kdtTabEntry.setName("kdtTabEntry");
        this.kdtTabTableEntry.setName("kdtTabTableEntry");
        this.txttableName.setName("txttableName");
        this.txtfileViewNode.setName("txtfileViewNode");
        this.txtfileUpNode.setName("txtfileUpNode");
        this.btnEditBill.setName("btnEditBill");
        this.btnAddTab.setName("btnAddTab");
        this.btnRemoveTab.setName("btnRemoveTab");
        this.btnAddLineRow.setName("btnAddLineRow");
        this.btnRemoveLineRow.setName("btnRemoveLineRow");
        this.btnRowUp.setName("btnRowUp");
        this.btnRowDown.setName("btnRowDown");
        // CoreUI		
        this.btnCopy.setEnabled(false);		
        this.btnCopy.setVisible(false);		
        this.btnFirst.setEnabled(false);		
        this.btnFirst.setVisible(false);		
        this.btnPre.setEnabled(false);		
        this.btnPre.setVisible(false);		
        this.btnNext.setEnabled(false);		
        this.btnNext.setVisible(false);		
        this.btnLast.setEnabled(false);		
        this.btnLast.setVisible(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnCreateFrom.setEnabled(false);		
        this.btnCreateFrom.setVisible(false);		
        this.btnCreateTo.setEnabled(false);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemCopyLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setEnabled(false);		
        this.contLastUpdateUser.setVisible(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);		
        this.contLastUpdateTime.setVisible(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);		
        this.contAuditor.setVisible(false);
        // contauditTitle		
        this.contauditTitle.setBoundLabelText(resHelper.getString("contauditTitle.boundLabelText"));		
        this.contauditTitle.setBoundLabelLength(100);		
        this.contauditTitle.setBoundLabelUnderline(true);		
        this.contauditTitle.setVisible(true);
        // contbostype		
        this.contbostype.setBoundLabelText(resHelper.getString("contbostype.boundLabelText"));		
        this.contbostype.setBoundLabelLength(100);		
        this.contbostype.setBoundLabelUnderline(true);		
        this.contbostype.setVisible(true);
        // conturl		
        this.conturl.setBoundLabelText(resHelper.getString("conturl.boundLabelText"));		
        this.conturl.setBoundLabelLength(100);		
        this.conturl.setBoundLabelUnderline(true);		
        this.conturl.setVisible(false);
        // kDTabbedPane1
        // conttableName		
        this.conttableName.setBoundLabelText(resHelper.getString("conttableName.boundLabelText"));		
        this.conttableName.setBoundLabelLength(100);		
        this.conttableName.setBoundLabelUnderline(true);		
        this.conttableName.setVisible(true);
        // contfileViewNode		
        this.contfileViewNode.setBoundLabelText(resHelper.getString("contfileViewNode.boundLabelText"));		
        this.contfileViewNode.setBoundLabelLength(100);		
        this.contfileViewNode.setBoundLabelUnderline(true);		
        this.contfileViewNode.setVisible(true);
        // contfileUpNode		
        this.contfileUpNode.setBoundLabelText(resHelper.getString("contfileUpNode.boundLabelText"));		
        this.contfileUpNode.setBoundLabelLength(100);		
        this.contfileUpNode.setBoundLabelUnderline(true);		
        this.contfileUpNode.setVisible(true);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setTimeEnabled(true);		
        this.kDDateLastUpdateTime.setEnabled(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // pkBizDate		
        this.pkBizDate.setVisible(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);
        // txtauditTitle		
        this.txtauditTitle.setVisible(true);		
        this.txtauditTitle.setHorizontalAlignment(2);		
        this.txtauditTitle.setMaxLength(100);		
        this.txtauditTitle.setRequired(false);
        // txtbostype		
        this.txtbostype.setVisible(true);		
        this.txtbostype.setHorizontalAlignment(2);		
        this.txtbostype.setMaxLength(100);		
        this.txtbostype.setRequired(false);
        // txturl		
        this.txturl.setVisible(true);		
        this.txturl.setHorizontalAlignment(2);		
        this.txturl.setMaxLength(100);		
        this.txturl.setRequired(false);		
        this.txturl.setEnabled(false);
        // kDPanel1
        // kDPanel2
        // kdtEntrys
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"entityField\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"displayName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"entityAttributes\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"dbName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"fieldType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"crossColumn\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"belongToRow\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"isEdit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"isPhone\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"editState\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"showNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"editNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"initValue\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lablePro\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"requiredNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"toHideNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{entityField}</t:Cell><t:Cell>$Resource{displayName}</t:Cell><t:Cell>$Resource{entityAttributes}</t:Cell><t:Cell>$Resource{dbName}</t:Cell><t:Cell>$Resource{fieldType}</t:Cell><t:Cell>$Resource{crossColumn}</t:Cell><t:Cell>$Resource{belongToRow}</t:Cell><t:Cell>$Resource{isEdit}</t:Cell><t:Cell>$Resource{isPhone}</t:Cell><t:Cell>$Resource{editState}</t:Cell><t:Cell>$Resource{showNode}</t:Cell><t:Cell>$Resource{editNode}</t:Cell><t:Cell>$Resource{initValue}</t:Cell><t:Cell>$Resource{lablePro}</t:Cell><t:Cell>$Resource{requiredNode}</t:Cell><t:Cell>$Resource{toHideNode}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));
        this.kdtEntrys.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntrys_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtEntrys.putBindContents("editData",new String[] {"id","","displayName","entityAttributes","dbName","fieldType","crossColumn","belongToRow","isEdit","isPhone","editState","showNode","editNode","initValue","lablePro","requiredNode","toHideNode"});


        this.kdtEntrys.checkParsed();
        KDTextField kdtEntrys_displayName_TextField = new KDTextField();
        kdtEntrys_displayName_TextField.setName("kdtEntrys_displayName_TextField");
        kdtEntrys_displayName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_displayName_CellEditor = new KDTDefaultCellEditor(kdtEntrys_displayName_TextField);
        this.kdtEntrys.getColumn("displayName").setEditor(kdtEntrys_displayName_CellEditor);
        KDTextField kdtEntrys_entityAttributes_TextField = new KDTextField();
        kdtEntrys_entityAttributes_TextField.setName("kdtEntrys_entityAttributes_TextField");
        kdtEntrys_entityAttributes_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_entityAttributes_CellEditor = new KDTDefaultCellEditor(kdtEntrys_entityAttributes_TextField);
        this.kdtEntrys.getColumn("entityAttributes").setEditor(kdtEntrys_entityAttributes_CellEditor);
        KDTextField kdtEntrys_dbName_TextField = new KDTextField();
        kdtEntrys_dbName_TextField.setName("kdtEntrys_dbName_TextField");
        kdtEntrys_dbName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_dbName_CellEditor = new KDTDefaultCellEditor(kdtEntrys_dbName_TextField);
        this.kdtEntrys.getColumn("dbName").setEditor(kdtEntrys_dbName_CellEditor);
        KDTextField kdtEntrys_fieldType_TextField = new KDTextField();
        kdtEntrys_fieldType_TextField.setName("kdtEntrys_fieldType_TextField");
        kdtEntrys_fieldType_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_fieldType_CellEditor = new KDTDefaultCellEditor(kdtEntrys_fieldType_TextField);
        this.kdtEntrys.getColumn("fieldType").setEditor(kdtEntrys_fieldType_CellEditor);
        KDFormattedTextField kdtEntrys_crossColumn_TextField = new KDFormattedTextField();
        kdtEntrys_crossColumn_TextField.setName("kdtEntrys_crossColumn_TextField");
        kdtEntrys_crossColumn_TextField.setVisible(true);
        kdtEntrys_crossColumn_TextField.setEditable(true);
        kdtEntrys_crossColumn_TextField.setHorizontalAlignment(2);
        kdtEntrys_crossColumn_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntrys_crossColumn_CellEditor = new KDTDefaultCellEditor(kdtEntrys_crossColumn_TextField);
        this.kdtEntrys.getColumn("crossColumn").setEditor(kdtEntrys_crossColumn_CellEditor);
        KDFormattedTextField kdtEntrys_belongToRow_TextField = new KDFormattedTextField();
        kdtEntrys_belongToRow_TextField.setName("kdtEntrys_belongToRow_TextField");
        kdtEntrys_belongToRow_TextField.setVisible(true);
        kdtEntrys_belongToRow_TextField.setEditable(true);
        kdtEntrys_belongToRow_TextField.setHorizontalAlignment(2);
        kdtEntrys_belongToRow_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntrys_belongToRow_CellEditor = new KDTDefaultCellEditor(kdtEntrys_belongToRow_TextField);
        this.kdtEntrys.getColumn("belongToRow").setEditor(kdtEntrys_belongToRow_CellEditor);
        KDCheckBox kdtEntrys_isEdit_CheckBox = new KDCheckBox();
        kdtEntrys_isEdit_CheckBox.setName("kdtEntrys_isEdit_CheckBox");
        KDTDefaultCellEditor kdtEntrys_isEdit_CellEditor = new KDTDefaultCellEditor(kdtEntrys_isEdit_CheckBox);
        this.kdtEntrys.getColumn("isEdit").setEditor(kdtEntrys_isEdit_CellEditor);
        KDCheckBox kdtEntrys_isPhone_CheckBox = new KDCheckBox();
        kdtEntrys_isPhone_CheckBox.setName("kdtEntrys_isPhone_CheckBox");
        KDTDefaultCellEditor kdtEntrys_isPhone_CellEditor = new KDTDefaultCellEditor(kdtEntrys_isPhone_CheckBox);
        this.kdtEntrys.getColumn("isPhone").setEditor(kdtEntrys_isPhone_CellEditor);
        KDTextField kdtEntrys_editState_TextField = new KDTextField();
        kdtEntrys_editState_TextField.setName("kdtEntrys_editState_TextField");
        kdtEntrys_editState_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_editState_CellEditor = new KDTDefaultCellEditor(kdtEntrys_editState_TextField);
        this.kdtEntrys.getColumn("editState").setEditor(kdtEntrys_editState_CellEditor);
        KDTextField kdtEntrys_showNode_TextField = new KDTextField();
        kdtEntrys_showNode_TextField.setName("kdtEntrys_showNode_TextField");
        kdtEntrys_showNode_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtEntrys_showNode_CellEditor = new KDTDefaultCellEditor(kdtEntrys_showNode_TextField);
        this.kdtEntrys.getColumn("showNode").setEditor(kdtEntrys_showNode_CellEditor);
        KDTextField kdtEntrys_editNode_TextField = new KDTextField();
        kdtEntrys_editNode_TextField.setName("kdtEntrys_editNode_TextField");
        kdtEntrys_editNode_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtEntrys_editNode_CellEditor = new KDTDefaultCellEditor(kdtEntrys_editNode_TextField);
        this.kdtEntrys.getColumn("editNode").setEditor(kdtEntrys_editNode_CellEditor);
        KDTextField kdtEntrys_initValue_TextField = new KDTextField();
        kdtEntrys_initValue_TextField.setName("kdtEntrys_initValue_TextField");
        kdtEntrys_initValue_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtEntrys_initValue_CellEditor = new KDTDefaultCellEditor(kdtEntrys_initValue_TextField);
        this.kdtEntrys.getColumn("initValue").setEditor(kdtEntrys_initValue_CellEditor);
        KDTextField kdtEntrys_lablePro_TextField = new KDTextField();
        kdtEntrys_lablePro_TextField.setName("kdtEntrys_lablePro_TextField");
        kdtEntrys_lablePro_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtEntrys_lablePro_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lablePro_TextField);
        this.kdtEntrys.getColumn("lablePro").setEditor(kdtEntrys_lablePro_CellEditor);
        KDTextField kdtEntrys_requiredNode_TextField = new KDTextField();
        kdtEntrys_requiredNode_TextField.setName("kdtEntrys_requiredNode_TextField");
        kdtEntrys_requiredNode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_requiredNode_CellEditor = new KDTDefaultCellEditor(kdtEntrys_requiredNode_TextField);
        this.kdtEntrys.getColumn("requiredNode").setEditor(kdtEntrys_requiredNode_CellEditor);
        KDTextField kdtEntrys_toHideNode_TextField = new KDTextField();
        kdtEntrys_toHideNode_TextField.setName("kdtEntrys_toHideNode_TextField");
        kdtEntrys_toHideNode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_toHideNode_CellEditor = new KDTDefaultCellEditor(kdtEntrys_toHideNode_TextField);
        this.kdtEntrys.getColumn("toHideNode").setEditor(kdtEntrys_toHideNode_CellEditor);
        // kdtTabEntry
		String kdtTabEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"tabName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"tabAlies\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"entryAtt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"entryBostype\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"tabTableName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isPhoneDisplay\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{tabName}</t:Cell><t:Cell>$Resource{tabAlies}</t:Cell><t:Cell>$Resource{entryAtt}</t:Cell><t:Cell>$Resource{entryBostype}</t:Cell><t:Cell>$Resource{tabTableName}</t:Cell><t:Cell>$Resource{isPhoneDisplay}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtTabEntry.setFormatXml(resHelper.translateString("kdtTabEntry",kdtTabEntryStrXML));

                this.kdtTabEntry.putBindContents("editData",new String[] {"seq","tabName","tabAlies","entryAtt","entryBostype","tabTableName","isPhoneDisplay"});


        this.kdtTabEntry.checkParsed();
        KDTextField kdtTabEntry_tabName_TextField = new KDTextField();
        kdtTabEntry_tabName_TextField.setName("kdtTabEntry_tabName_TextField");
        kdtTabEntry_tabName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabEntry_tabName_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_tabName_TextField);
        this.kdtTabEntry.getColumn("tabName").setEditor(kdtTabEntry_tabName_CellEditor);
        KDTextField kdtTabEntry_tabAlies_TextField = new KDTextField();
        kdtTabEntry_tabAlies_TextField.setName("kdtTabEntry_tabAlies_TextField");
        kdtTabEntry_tabAlies_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabEntry_tabAlies_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_tabAlies_TextField);
        this.kdtTabEntry.getColumn("tabAlies").setEditor(kdtTabEntry_tabAlies_CellEditor);
        KDTextField kdtTabEntry_entryAtt_TextField = new KDTextField();
        kdtTabEntry_entryAtt_TextField.setName("kdtTabEntry_entryAtt_TextField");
        kdtTabEntry_entryAtt_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabEntry_entryAtt_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_entryAtt_TextField);
        this.kdtTabEntry.getColumn("entryAtt").setEditor(kdtTabEntry_entryAtt_CellEditor);
        KDTextField kdtTabEntry_entryBostype_TextField = new KDTextField();
        kdtTabEntry_entryBostype_TextField.setName("kdtTabEntry_entryBostype_TextField");
        kdtTabEntry_entryBostype_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabEntry_entryBostype_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_entryBostype_TextField);
        this.kdtTabEntry.getColumn("entryBostype").setEditor(kdtTabEntry_entryBostype_CellEditor);
        KDTextField kdtTabEntry_tabTableName_TextField = new KDTextField();
        kdtTabEntry_tabTableName_TextField.setName("kdtTabEntry_tabTableName_TextField");
        kdtTabEntry_tabTableName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabEntry_tabTableName_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_tabTableName_TextField);
        this.kdtTabEntry.getColumn("tabTableName").setEditor(kdtTabEntry_tabTableName_CellEditor);
        KDCheckBox kdtTabEntry_isPhoneDisplay_CheckBox = new KDCheckBox();
        kdtTabEntry_isPhoneDisplay_CheckBox.setName("kdtTabEntry_isPhoneDisplay_CheckBox");
        KDTDefaultCellEditor kdtTabEntry_isPhoneDisplay_CellEditor = new KDTDefaultCellEditor(kdtTabEntry_isPhoneDisplay_CheckBox);
        this.kdtTabEntry.getColumn("isPhoneDisplay").setEditor(kdtTabEntry_isPhoneDisplay_CellEditor);
        // kdtTabTableEntry
		String kdtTabTableEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"displayName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"entityAttributes\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"dbName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"fieldType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"belongToRow\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol5\" /><t:Column t:key=\"crossColumn\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol6\" /><t:Column t:key=\"isEdit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"tableName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isPhone\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"editState\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"colWidth\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol11\" /><t:Column t:key=\"showNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"editNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"initValue\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lablePro\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"requiredNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"toHideNode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{displayName}</t:Cell><t:Cell>$Resource{entityAttributes}</t:Cell><t:Cell>$Resource{dbName}</t:Cell><t:Cell>$Resource{fieldType}</t:Cell><t:Cell>$Resource{belongToRow}</t:Cell><t:Cell>$Resource{crossColumn}</t:Cell><t:Cell>$Resource{isEdit}</t:Cell><t:Cell>$Resource{tableName}</t:Cell><t:Cell>$Resource{isPhone}</t:Cell><t:Cell>$Resource{editState}</t:Cell><t:Cell>$Resource{colWidth}</t:Cell><t:Cell>$Resource{showNode}</t:Cell><t:Cell>$Resource{editNode}</t:Cell><t:Cell>$Resource{initValue}</t:Cell><t:Cell>$Resource{lablePro}</t:Cell><t:Cell>$Resource{requiredNode}</t:Cell><t:Cell>$Resource{toHideNode}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtTabTableEntry.setFormatXml(resHelper.translateString("kdtTabTableEntry",kdtTabTableEntryStrXML));

                this.kdtTabTableEntry.putBindContents("editData",new String[] {"seq","displayName","entityAttributes","dbName","fieldType","belongToRow","crossColumn","isEdit","tableName","isPhone","editState","colWidth","showNode","editNode","initValue","lablePro","requiredNode","toHideNode"});


        this.kdtTabTableEntry.checkParsed();
        KDTextField kdtTabTableEntry_displayName_TextField = new KDTextField();
        kdtTabTableEntry_displayName_TextField.setName("kdtTabTableEntry_displayName_TextField");
        kdtTabTableEntry_displayName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_displayName_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_displayName_TextField);
        this.kdtTabTableEntry.getColumn("displayName").setEditor(kdtTabTableEntry_displayName_CellEditor);
        KDTextField kdtTabTableEntry_entityAttributes_TextField = new KDTextField();
        kdtTabTableEntry_entityAttributes_TextField.setName("kdtTabTableEntry_entityAttributes_TextField");
        kdtTabTableEntry_entityAttributes_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_entityAttributes_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_entityAttributes_TextField);
        this.kdtTabTableEntry.getColumn("entityAttributes").setEditor(kdtTabTableEntry_entityAttributes_CellEditor);
        KDTextField kdtTabTableEntry_dbName_TextField = new KDTextField();
        kdtTabTableEntry_dbName_TextField.setName("kdtTabTableEntry_dbName_TextField");
        kdtTabTableEntry_dbName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_dbName_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_dbName_TextField);
        this.kdtTabTableEntry.getColumn("dbName").setEditor(kdtTabTableEntry_dbName_CellEditor);
        KDTextField kdtTabTableEntry_fieldType_TextField = new KDTextField();
        kdtTabTableEntry_fieldType_TextField.setName("kdtTabTableEntry_fieldType_TextField");
        kdtTabTableEntry_fieldType_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_fieldType_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_fieldType_TextField);
        this.kdtTabTableEntry.getColumn("fieldType").setEditor(kdtTabTableEntry_fieldType_CellEditor);
        KDFormattedTextField kdtTabTableEntry_belongToRow_TextField = new KDFormattedTextField();
        kdtTabTableEntry_belongToRow_TextField.setName("kdtTabTableEntry_belongToRow_TextField");
        kdtTabTableEntry_belongToRow_TextField.setVisible(true);
        kdtTabTableEntry_belongToRow_TextField.setEditable(true);
        kdtTabTableEntry_belongToRow_TextField.setHorizontalAlignment(2);
        kdtTabTableEntry_belongToRow_TextField.setDataType(0);
        KDTDefaultCellEditor kdtTabTableEntry_belongToRow_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_belongToRow_TextField);
        this.kdtTabTableEntry.getColumn("belongToRow").setEditor(kdtTabTableEntry_belongToRow_CellEditor);
        KDFormattedTextField kdtTabTableEntry_crossColumn_TextField = new KDFormattedTextField();
        kdtTabTableEntry_crossColumn_TextField.setName("kdtTabTableEntry_crossColumn_TextField");
        kdtTabTableEntry_crossColumn_TextField.setVisible(true);
        kdtTabTableEntry_crossColumn_TextField.setEditable(true);
        kdtTabTableEntry_crossColumn_TextField.setHorizontalAlignment(2);
        kdtTabTableEntry_crossColumn_TextField.setDataType(0);
        KDTDefaultCellEditor kdtTabTableEntry_crossColumn_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_crossColumn_TextField);
        this.kdtTabTableEntry.getColumn("crossColumn").setEditor(kdtTabTableEntry_crossColumn_CellEditor);
        KDCheckBox kdtTabTableEntry_isEdit_CheckBox = new KDCheckBox();
        kdtTabTableEntry_isEdit_CheckBox.setName("kdtTabTableEntry_isEdit_CheckBox");
        KDTDefaultCellEditor kdtTabTableEntry_isEdit_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_isEdit_CheckBox);
        this.kdtTabTableEntry.getColumn("isEdit").setEditor(kdtTabTableEntry_isEdit_CellEditor);
        KDTextField kdtTabTableEntry_tableName_TextField = new KDTextField();
        kdtTabTableEntry_tableName_TextField.setName("kdtTabTableEntry_tableName_TextField");
        kdtTabTableEntry_tableName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_tableName_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_tableName_TextField);
        this.kdtTabTableEntry.getColumn("tableName").setEditor(kdtTabTableEntry_tableName_CellEditor);
        KDCheckBox kdtTabTableEntry_isPhone_CheckBox = new KDCheckBox();
        kdtTabTableEntry_isPhone_CheckBox.setName("kdtTabTableEntry_isPhone_CheckBox");
        KDTDefaultCellEditor kdtTabTableEntry_isPhone_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_isPhone_CheckBox);
        this.kdtTabTableEntry.getColumn("isPhone").setEditor(kdtTabTableEntry_isPhone_CellEditor);
        KDTextField kdtTabTableEntry_editState_TextField = new KDTextField();
        kdtTabTableEntry_editState_TextField.setName("kdtTabTableEntry_editState_TextField");
        kdtTabTableEntry_editState_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_editState_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_editState_TextField);
        this.kdtTabTableEntry.getColumn("editState").setEditor(kdtTabTableEntry_editState_CellEditor);
        KDFormattedTextField kdtTabTableEntry_colWidth_TextField = new KDFormattedTextField();
        kdtTabTableEntry_colWidth_TextField.setName("kdtTabTableEntry_colWidth_TextField");
        kdtTabTableEntry_colWidth_TextField.setVisible(true);
        kdtTabTableEntry_colWidth_TextField.setEditable(true);
        kdtTabTableEntry_colWidth_TextField.setHorizontalAlignment(2);
        kdtTabTableEntry_colWidth_TextField.setDataType(0);
        KDTDefaultCellEditor kdtTabTableEntry_colWidth_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_colWidth_TextField);
        this.kdtTabTableEntry.getColumn("colWidth").setEditor(kdtTabTableEntry_colWidth_CellEditor);
        KDTextField kdtTabTableEntry_showNode_TextField = new KDTextField();
        kdtTabTableEntry_showNode_TextField.setName("kdtTabTableEntry_showNode_TextField");
        kdtTabTableEntry_showNode_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtTabTableEntry_showNode_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_showNode_TextField);
        this.kdtTabTableEntry.getColumn("showNode").setEditor(kdtTabTableEntry_showNode_CellEditor);
        KDTextField kdtTabTableEntry_editNode_TextField = new KDTextField();
        kdtTabTableEntry_editNode_TextField.setName("kdtTabTableEntry_editNode_TextField");
        kdtTabTableEntry_editNode_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtTabTableEntry_editNode_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_editNode_TextField);
        this.kdtTabTableEntry.getColumn("editNode").setEditor(kdtTabTableEntry_editNode_CellEditor);
        KDTextField kdtTabTableEntry_initValue_TextField = new KDTextField();
        kdtTabTableEntry_initValue_TextField.setName("kdtTabTableEntry_initValue_TextField");
        kdtTabTableEntry_initValue_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtTabTableEntry_initValue_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_initValue_TextField);
        this.kdtTabTableEntry.getColumn("initValue").setEditor(kdtTabTableEntry_initValue_CellEditor);
        KDTextField kdtTabTableEntry_lablePro_TextField = new KDTextField();
        kdtTabTableEntry_lablePro_TextField.setName("kdtTabTableEntry_lablePro_TextField");
        kdtTabTableEntry_lablePro_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtTabTableEntry_lablePro_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_lablePro_TextField);
        this.kdtTabTableEntry.getColumn("lablePro").setEditor(kdtTabTableEntry_lablePro_CellEditor);
        KDTextField kdtTabTableEntry_requiredNode_TextField = new KDTextField();
        kdtTabTableEntry_requiredNode_TextField.setName("kdtTabTableEntry_requiredNode_TextField");
        kdtTabTableEntry_requiredNode_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtTabTableEntry_requiredNode_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_requiredNode_TextField);
        this.kdtTabTableEntry.getColumn("requiredNode").setEditor(kdtTabTableEntry_requiredNode_CellEditor);
        KDTextField kdtTabTableEntry_toHideNode_TextField = new KDTextField();
        kdtTabTableEntry_toHideNode_TextField.setName("kdtTabTableEntry_toHideNode_TextField");
        kdtTabTableEntry_toHideNode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtTabTableEntry_toHideNode_CellEditor = new KDTDefaultCellEditor(kdtTabTableEntry_toHideNode_TextField);
        this.kdtTabTableEntry.getColumn("toHideNode").setEditor(kdtTabTableEntry_toHideNode_CellEditor);
        // txttableName		
        this.txttableName.setVisible(true);		
        this.txttableName.setHorizontalAlignment(2);		
        this.txttableName.setMaxLength(100);		
        this.txttableName.setRequired(false);
        // txtfileViewNode		
        this.txtfileViewNode.setVisible(true);		
        this.txtfileViewNode.setHorizontalAlignment(2);		
        this.txtfileViewNode.setMaxLength(200);		
        this.txtfileViewNode.setRequired(false);
        // txtfileUpNode		
        this.txtfileUpNode.setVisible(true);		
        this.txtfileUpNode.setHorizontalAlignment(2);		
        this.txtfileUpNode.setMaxLength(200);		
        this.txtfileUpNode.setRequired(false);
        // btnEditBill
        this.btnEditBill.setAction((IItemAction)ActionProxyFactory.getProxy(actionEditBill, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnEditBill.setText(resHelper.getString("btnEditBill.text"));
        // btnAddTab
        this.btnAddTab.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddTab, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddTab.setText(resHelper.getString("btnAddTab.text"));
        // btnRemoveTab
        this.btnRemoveTab.setAction((IItemAction)ActionProxyFactory.getProxy(actionRemoveTab, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRemoveTab.setText(resHelper.getString("btnRemoveTab.text"));
        // btnAddLineRow
        this.btnAddLineRow.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddLineRow, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddLineRow.setText(resHelper.getString("btnAddLineRow.text"));
        // btnRemoveLineRow
        this.btnRemoveLineRow.setAction((IItemAction)ActionProxyFactory.getProxy(actionRemoveLineRow, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRemoveLineRow.setText(resHelper.getString("btnRemoveLineRow.text"));
        // btnRowUp
        this.btnRowUp.setAction((IItemAction)ActionProxyFactory.getProxy(actionRowUp, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRowUp.setText(resHelper.getString("btnRowUp.text"));
        // btnRowDown
        this.btnRowDown.setAction((IItemAction)ActionProxyFactory.getProxy(actionRowDown, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRowDown.setText(resHelper.getString("btnRowDown.text"));
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kdtTabTableEntry,kdtTabEntry,kDDateLastUpdateTime,prmtLastUpdateUser,kDDateCreateTime,prmtCreator,prmtAuditor,txtDescription,pkBizDate,txtNumber,txturl,txtbostype,txtauditTitle,kdtEntrys,txttableName,txtfileViewNode,txtfileUpNode}));
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
        this.setBounds(new Rectangle(0, 0, 1019, 712));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1019, 712));
        contCreator.setBounds(new Rectangle(10, 584, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(10, 584, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(676, 584, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(676, 584, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contLastUpdateUser.setBounds(new Rectangle(10, 608, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(10, 608, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(676, 608, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(676, 608, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contNumber.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(10, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(343, 34, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(343, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDescription.setBounds(new Rectangle(676, 34, 270, 19));
        this.add(contDescription, new KDLayout.Constraints(676, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(343, 608, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(343, 608, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contauditTitle.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contauditTitle, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbostype.setBounds(new Rectangle(343, 10, 270, 19));
        this.add(contbostype, new KDLayout.Constraints(343, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conturl.setBounds(new Rectangle(343, 584, 270, 19));
        this.add(conturl, new KDLayout.Constraints(343, 584, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(10, 104, 982, 477));
        this.add(kDTabbedPane1, new KDLayout.Constraints(10, 104, 982, 477, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttableName.setBounds(new Rectangle(676, 10, 270, 19));
        this.add(conttableName, new KDLayout.Constraints(676, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contfileViewNode.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contfileViewNode, new KDLayout.Constraints(10, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contfileUpNode.setBounds(new Rectangle(343, 58, 270, 19));
        this.add(contfileUpNode, new KDLayout.Constraints(343, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contauditTitle
        contauditTitle.setBoundEditor(txtauditTitle);
        //contbostype
        contbostype.setBoundEditor(txtbostype);
        //conturl
        conturl.setBoundEditor(txturl);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPanel1, resHelper.getString("kDPanel1.constraints"));
        kDTabbedPane1.add(kDPanel2, resHelper.getString("kDPanel2.constraints"));
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(0, 0, 981, 444));        kdtEntrys.setBounds(new Rectangle(5, 8, 964, 395));
        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.custom.UIToolEntryInfo(),null,false);
        kDPanel1.add(kdtEntrys_detailPanel, new KDLayout.Constraints(5, 8, 964, 395, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel2
        kDPanel2.setLayout(new KDLayout());
        kDPanel2.putClientProperty("OriginalBounds", new Rectangle(0, 0, 981, 444));        kdtTabEntry.setBounds(new Rectangle(2, 2, 972, 170));
        kdtTabEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtTabEntry,new com.kingdee.eas.custom.UIToolTabEntryInfo(),null,false);
        kDPanel2.add(kdtTabEntry_detailPanel, new KDLayout.Constraints(2, 2, 972, 170, KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kdtTabTableEntry.setBounds(new Rectangle(2, 183, 971, 226));
        kdtTabTableEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtTabTableEntry,new com.kingdee.eas.custom.UIToolTabTableEntryInfo(),null,false);
        kDPanel2.add(kdtTabTableEntry_detailPanel, new KDLayout.Constraints(2, 183, 971, 226, KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //conttableName
        conttableName.setBoundEditor(txttableName);
        //contfileViewNode
        contfileViewNode.setBoundEditor(txtfileViewNode);
        //contfileUpNode
        contfileUpNode.setBoundEditor(txtfileUpNode);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
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
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
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
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
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
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(MenuItemPCVoucher);
        menuBiz.add(menuItemDelPCVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
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
        this.toolBar.add(btnSave);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
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
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnPCVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnDelPCVoucher);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnEditBill);
        this.toolBar.add(btnAddTab);
        this.toolBar.add(btnRemoveTab);
        this.toolBar.add(btnAddLineRow);
        this.toolBar.add(btnRemoveLineRow);
        this.toolBar.add(btnRowUp);
        this.toolBar.add(btnRowDown);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("auditTitle", String.class, this.txtauditTitle, "text");
		dataBinder.registerBinding("bostype", String.class, this.txtbostype, "text");
		dataBinder.registerBinding("url", String.class, this.txturl, "text");
		dataBinder.registerBinding("entrys.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntrys, "id.text");
		dataBinder.registerBinding("entrys", com.kingdee.eas.custom.UIToolEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.displayName", String.class, this.kdtEntrys, "displayName.text");
		dataBinder.registerBinding("entrys.entityAttributes", String.class, this.kdtEntrys, "entityAttributes.text");
		dataBinder.registerBinding("entrys.dbName", String.class, this.kdtEntrys, "dbName.text");
		dataBinder.registerBinding("entrys.fieldType", String.class, this.kdtEntrys, "fieldType.text");
		dataBinder.registerBinding("entrys.crossColumn", int.class, this.kdtEntrys, "crossColumn.text");
		dataBinder.registerBinding("entrys.belongToRow", int.class, this.kdtEntrys, "belongToRow.text");
		dataBinder.registerBinding("entrys.isEdit", boolean.class, this.kdtEntrys, "isEdit.text");
		dataBinder.registerBinding("entrys.isPhone", boolean.class, this.kdtEntrys, "isPhone.text");
		dataBinder.registerBinding("entrys.editState", String.class, this.kdtEntrys, "editState.text");
		dataBinder.registerBinding("entrys.showNode", String.class, this.kdtEntrys, "showNode.text");
		dataBinder.registerBinding("entrys.editNode", String.class, this.kdtEntrys, "editNode.text");
		dataBinder.registerBinding("entrys.initValue", String.class, this.kdtEntrys, "initValue.text");
		dataBinder.registerBinding("entrys.lablePro", String.class, this.kdtEntrys, "lablePro.text");
		dataBinder.registerBinding("entrys.requiredNode", String.class, this.kdtEntrys, "requiredNode.text");
		dataBinder.registerBinding("entrys.toHideNode", String.class, this.kdtEntrys, "toHideNode.text");
		dataBinder.registerBinding("TabEntry.seq", int.class, this.kdtTabEntry, "seq.text");
		dataBinder.registerBinding("TabEntry", com.kingdee.eas.custom.UIToolTabEntryInfo.class, this.kdtTabEntry, "userObject");
		dataBinder.registerBinding("TabEntry.tabName", String.class, this.kdtTabEntry, "tabName.text");
		dataBinder.registerBinding("TabEntry.tabAlies", String.class, this.kdtTabEntry, "tabAlies.text");
		dataBinder.registerBinding("TabEntry.entryAtt", String.class, this.kdtTabEntry, "entryAtt.text");
		dataBinder.registerBinding("TabEntry.entryBostype", String.class, this.kdtTabEntry, "entryBostype.text");
		dataBinder.registerBinding("TabEntry.tabTableName", String.class, this.kdtTabEntry, "tabTableName.text");
		dataBinder.registerBinding("TabEntry.isPhoneDisplay", boolean.class, this.kdtTabEntry, "isPhoneDisplay.text");
		dataBinder.registerBinding("TabTableEntry.seq", int.class, this.kdtTabTableEntry, "seq.text");
		dataBinder.registerBinding("TabTableEntry", com.kingdee.eas.custom.UIToolTabTableEntryInfo.class, this.kdtTabTableEntry, "userObject");
		dataBinder.registerBinding("TabTableEntry.displayName", String.class, this.kdtTabTableEntry, "displayName.text");
		dataBinder.registerBinding("TabTableEntry.entityAttributes", String.class, this.kdtTabTableEntry, "entityAttributes.text");
		dataBinder.registerBinding("TabTableEntry.dbName", String.class, this.kdtTabTableEntry, "dbName.text");
		dataBinder.registerBinding("TabTableEntry.fieldType", String.class, this.kdtTabTableEntry, "fieldType.text");
		dataBinder.registerBinding("TabTableEntry.belongToRow", int.class, this.kdtTabTableEntry, "belongToRow.text");
		dataBinder.registerBinding("TabTableEntry.crossColumn", int.class, this.kdtTabTableEntry, "crossColumn.text");
		dataBinder.registerBinding("TabTableEntry.isEdit", boolean.class, this.kdtTabTableEntry, "isEdit.text");
		dataBinder.registerBinding("TabTableEntry.tableName", String.class, this.kdtTabTableEntry, "tableName.text");
		dataBinder.registerBinding("TabTableEntry.isPhone", boolean.class, this.kdtTabTableEntry, "isPhone.text");
		dataBinder.registerBinding("TabTableEntry.editState", String.class, this.kdtTabTableEntry, "editState.text");
		dataBinder.registerBinding("TabTableEntry.colWidth", int.class, this.kdtTabTableEntry, "colWidth.text");
		dataBinder.registerBinding("TabTableEntry.showNode", String.class, this.kdtTabTableEntry, "showNode.text");
		dataBinder.registerBinding("TabTableEntry.editNode", String.class, this.kdtTabTableEntry, "editNode.text");
		dataBinder.registerBinding("TabTableEntry.initValue", String.class, this.kdtTabTableEntry, "initValue.text");
		dataBinder.registerBinding("TabTableEntry.lablePro", String.class, this.kdtTabTableEntry, "lablePro.text");
		dataBinder.registerBinding("TabTableEntry.requiredNode", String.class, this.kdtTabTableEntry, "requiredNode.text");
		dataBinder.registerBinding("TabTableEntry.toHideNode", String.class, this.kdtTabTableEntry, "toHideNode.text");
		dataBinder.registerBinding("tableName", String.class, this.txttableName, "text");
		dataBinder.registerBinding("fileViewNode", String.class, this.txtfileViewNode, "text");
		dataBinder.registerBinding("fileUpNode", String.class, this.txtfileUpNode, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.app.UIToolEditUIHandler";
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
        this.kdtTabTableEntry.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.UIToolInfo)ov;
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
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditTitle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bostype", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("url", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.displayName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.entityAttributes", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.dbName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.fieldType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.crossColumn", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.belongToRow", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.isEdit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.isPhone", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.editState", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.showNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.editNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.initValue", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.lablePro", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.requiredNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.toHideNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.tabName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.tabAlies", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.entryAtt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.entryBostype", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.tabTableName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabEntry.isPhoneDisplay", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.displayName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.entityAttributes", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.dbName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.fieldType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.belongToRow", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.crossColumn", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.isEdit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.tableName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.isPhone", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.editState", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.colWidth", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.showNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.editNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.initValue", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.lablePro", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.requiredNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("TabTableEntry.toHideNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("tableName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("fileViewNode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("fileUpNode", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output kdtEntrys_editStopped method
     */
    protected void kdtEntrys_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
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
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
        sic.add(new SelectorItemInfo("createTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("auditTitle"));
        sic.add(new SelectorItemInfo("bostype"));
        sic.add(new SelectorItemInfo("url"));
    	sic.add(new SelectorItemInfo("entrys.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entrys.displayName"));
    	sic.add(new SelectorItemInfo("entrys.entityAttributes"));
    	sic.add(new SelectorItemInfo("entrys.dbName"));
    	sic.add(new SelectorItemInfo("entrys.fieldType"));
    	sic.add(new SelectorItemInfo("entrys.crossColumn"));
    	sic.add(new SelectorItemInfo("entrys.belongToRow"));
    	sic.add(new SelectorItemInfo("entrys.isEdit"));
    	sic.add(new SelectorItemInfo("entrys.isPhone"));
    	sic.add(new SelectorItemInfo("entrys.editState"));
    	sic.add(new SelectorItemInfo("entrys.showNode"));
    	sic.add(new SelectorItemInfo("entrys.editNode"));
    	sic.add(new SelectorItemInfo("entrys.initValue"));
    	sic.add(new SelectorItemInfo("entrys.lablePro"));
    	sic.add(new SelectorItemInfo("entrys.requiredNode"));
    	sic.add(new SelectorItemInfo("entrys.toHideNode"));
    	sic.add(new SelectorItemInfo("TabEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("TabEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("TabEntry.tabName"));
    	sic.add(new SelectorItemInfo("TabEntry.tabAlies"));
    	sic.add(new SelectorItemInfo("TabEntry.entryAtt"));
    	sic.add(new SelectorItemInfo("TabEntry.entryBostype"));
    	sic.add(new SelectorItemInfo("TabEntry.tabTableName"));
    	sic.add(new SelectorItemInfo("TabEntry.isPhoneDisplay"));
    	sic.add(new SelectorItemInfo("TabTableEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("TabTableEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("TabTableEntry.displayName"));
    	sic.add(new SelectorItemInfo("TabTableEntry.entityAttributes"));
    	sic.add(new SelectorItemInfo("TabTableEntry.dbName"));
    	sic.add(new SelectorItemInfo("TabTableEntry.fieldType"));
    	sic.add(new SelectorItemInfo("TabTableEntry.belongToRow"));
    	sic.add(new SelectorItemInfo("TabTableEntry.crossColumn"));
    	sic.add(new SelectorItemInfo("TabTableEntry.isEdit"));
    	sic.add(new SelectorItemInfo("TabTableEntry.tableName"));
    	sic.add(new SelectorItemInfo("TabTableEntry.isPhone"));
    	sic.add(new SelectorItemInfo("TabTableEntry.editState"));
    	sic.add(new SelectorItemInfo("TabTableEntry.colWidth"));
    	sic.add(new SelectorItemInfo("TabTableEntry.showNode"));
    	sic.add(new SelectorItemInfo("TabTableEntry.editNode"));
    	sic.add(new SelectorItemInfo("TabTableEntry.initValue"));
    	sic.add(new SelectorItemInfo("TabTableEntry.lablePro"));
    	sic.add(new SelectorItemInfo("TabTableEntry.requiredNode"));
    	sic.add(new SelectorItemInfo("TabTableEntry.toHideNode"));
        sic.add(new SelectorItemInfo("tableName"));
        sic.add(new SelectorItemInfo("fileViewNode"));
        sic.add(new SelectorItemInfo("fileUpNode"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionEditBill_actionPerformed method
     */
    public void actionEditBill_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddTab_actionPerformed method
     */
    public void actionAddTab_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRemoveTab_actionPerformed method
     */
    public void actionRemoveTab_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddLineRow_actionPerformed method
     */
    public void actionAddLineRow_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRemoveLineRow_actionPerformed method
     */
    public void actionRemoveLineRow_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionrRowUp_actionPerformed method
     */
    public void actionrRowUp_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRowDown_actionPerformed method
     */
    public void actionRowDown_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareActionEditBill(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionEditBill() {
    	return false;
    }
	public RequestContext prepareActionAddTab(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddTab() {
    	return false;
    }
	public RequestContext prepareActionRemoveTab(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRemoveTab() {
    	return false;
    }
	public RequestContext prepareActionAddLineRow(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddLineRow() {
    	return false;
    }
	public RequestContext prepareActionRemoveLineRow(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRemoveLineRow() {
    	return false;
    }
	public RequestContext prepareActionrRowUp(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionrRowUp() {
    	return false;
    }
	public RequestContext prepareActionRowDown(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRowDown() {
    	return false;
    }

    /**
     * output ActionEditBill class
     */     
    protected class ActionEditBill extends ItemAction {     
    
        public ActionEditBill()
        {
            this(null);
        }

        public ActionEditBill(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionEditBill.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEditBill.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEditBill.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionEditBill", "actionEditBill_actionPerformed", e);
        }
    }

    /**
     * output ActionAddTab class
     */     
    protected class ActionAddTab extends ItemAction {     
    
        public ActionAddTab()
        {
            this(null);
        }

        public ActionAddTab(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAddTab.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddTab.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddTab.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionAddTab", "actionAddTab_actionPerformed", e);
        }
    }

    /**
     * output ActionRemoveTab class
     */     
    protected class ActionRemoveTab extends ItemAction {     
    
        public ActionRemoveTab()
        {
            this(null);
        }

        public ActionRemoveTab(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionRemoveTab.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRemoveTab.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRemoveTab.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionRemoveTab", "actionRemoveTab_actionPerformed", e);
        }
    }

    /**
     * output ActionAddLineRow class
     */     
    protected class ActionAddLineRow extends ItemAction {     
    
        public ActionAddLineRow()
        {
            this(null);
        }

        public ActionAddLineRow(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAddLineRow.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddLineRow.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddLineRow.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionAddLineRow", "actionAddLineRow_actionPerformed", e);
        }
    }

    /**
     * output ActionRemoveLineRow class
     */     
    protected class ActionRemoveLineRow extends ItemAction {     
    
        public ActionRemoveLineRow()
        {
            this(null);
        }

        public ActionRemoveLineRow(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionRemoveLineRow.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRemoveLineRow.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRemoveLineRow.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionRemoveLineRow", "actionRemoveLineRow_actionPerformed", e);
        }
    }

    /**
     * output ActionrRowUp class
     */     
    protected class ActionrRowUp extends ItemAction {     
    
        public ActionrRowUp()
        {
            this(null);
        }

        public ActionrRowUp(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionrRowUp.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionrRowUp.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionrRowUp.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionrRowUp", "actionrRowUp_actionPerformed", e);
        }
    }

    /**
     * output ActionRowDown class
     */     
    protected class ActionRowDown extends ItemAction {     
    
        public ActionRowDown()
        {
            this(null);
        }

        public ActionRowDown(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionRowDown.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRowDown.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRowDown.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUIToolEditUI.this, "ActionRowDown", "actionRowDown_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.client", "UIToolEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.custom.client.UIToolEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.UIToolFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.UIToolInfo objectValue = new com.kingdee.eas.custom.UIToolInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/custom/UITool";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.app.UIToolQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntrys;
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