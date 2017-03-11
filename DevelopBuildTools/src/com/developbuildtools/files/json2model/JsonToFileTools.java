package com.developbuildtools.files.json2model;

import java.util.ArrayList;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.developbuildtools.utils.FilesUtils;
import com.developbuildtools.utils.StrBufferUtils;
import com.developbuildtools.utils.StringUtils;

/**
 * ����json ���� model+......
 * 
 * @author Carry
 * 
 */
public class JsonToFileTools {

	// android ʹ��ģ��
	public static String A_T_0 = "package %s.model;";// java�ļ� package
	public static String A_T_1 = "public class %sModel {";// claas ͷ��
	public static String A_T_2 = "public %sModel %s;";// ����model
	public static String A_T_3 = "public ArrayList<%sModel> %s;";// ����ArrayModel
	public static String A_T_4 = "public String %s;";// Ĭ������String
	public static String A_T_5 = "}";// ����

	// IOS ʹ��ģ��
	public static String I_T_0 = "#import <%sModel/%sModel.h>";// ios import
	public static String I_T_1 = "@interface %sModel : NSObject";// ios ͷ��
	public static String I_T_2 = "@property(nonatomic,strong)%sModel* %s;";// ����model
	public static String I_T_3 = "@property(nonatomic,strong)NSArray<%sModel *>* %s;";// ����ArrayModel
	public static String I_T_4 = "@property(nonatomic,strong)NSString* %s;";// Ĭ������String
	public static String I_T_5 = "@end";// ����
	public static String I_T_6 = "#import \"%sModel.h\"";// ����

	// test ������
	public static String packageName = "com.test.ing";
	public static String modelPathStr = "com\\model";
	public static String iosmodelPathStr = "com\\iosmodel";
	public static String reformPathStr = "com\\reformer";
	public static String reformImplPathStr = "com\\reformerImpl";
	public static String presenterImplPathStr = "com\\presenterImpl";

	// model ׼����list

	public static ArrayList<String> fileNameList = new ArrayList<>();

	// iosmodel ׼����list
	public static ArrayList<StringBuffer> iosList = new ArrayList<>();
	public static ArrayList<String> iosFileNameList = new ArrayList<>();

	// reform ׼����list
	public static ArrayList<StringBuffer> reformerList = new ArrayList<>();

	// ����json
	public static String testJson = "{\"password\":\"\",\"username\":\"����\"}";

	static StringBuffer jsonStrBuffer = new StringBuffer();

	public JsonToFileTools() {

	}

	// ִ����
	public void doingFunction() {
		// ��ȡ�ļ�����
		String readInfoText = FilesUtils.readTxtFile(FilesUtils.getHomePath()
				+ "\\testJson.txt");
		// ��ȡmodel
		String textJson = StringUtils.isNull(readInfoText) ? testJson
				: readInfoText;

		// android jsonתmodelִ�з���
		android_json2model("test", textJson);
		// ios jsonתmodelִ�з���
		ios_json2model("Foundation", textJson);

		// // ��ȡreformer
		// jsonToReformer("test");
		// // ��ȡreformerImpl
		// jsonToReformerImpl("test");
		// // ��ȡpresenterImpl
		// jsonToPresenterImpl("test");
	}

	/**
	 * android jsonתmodel ִ��
	 * 
	 * @param name
	 * @param json
	 */
	private void android_json2model(String name, String json) {
		ArrayList<StringBuffer> list = new ArrayList<>();
		ArrayList<String> fileNameList = new ArrayList<>();
		jsonToModel(name, json, list, fileNameList);
		for (int i = 0; i < list.size(); i++) {
			FilesUtils.makeStr(list.get(i).toString(), fileNameList.get(i)
					+ ".java", modelPathStr);
		}
	}

	/**
	 * ios jsonתmodel ִ��
	 * 
	 * @param name
	 * @param json
	 */
	private void ios_json2model(String name, String json) {
		ArrayList<StringBuffer> iosList = new ArrayList<>();
		ArrayList<String> iosFileNameList = new ArrayList<>();
		jsonToIOSModel(name, json, iosList, iosFileNameList);
		for (int i = 0; i < iosList.size(); i++) {
			FilesUtils.makeStr(iosList.get(i).toString(),
					iosFileNameList.get(i) + ".h", iosmodelPathStr);
		}
	}

	/**
	 * ����json ����model
	 * 
	 * @param name
	 * @param json
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public void jsonToModel(String name, String json,
			ArrayList<StringBuffer> list, ArrayList<String> fileNameList) {
		// ����json
		ArrayList<String> keyList = new ArrayList<>();
		ArrayList<String> valueList = new ArrayList<>();
		// list json
		ArrayList<String> arrkeyList = new ArrayList<>();
		ArrayList<String> arrvalueList = new ArrayList<>();
		// ������ļ�����
		StrBufferUtils strBuffer = new StrBufferUtils();

		// ��ȡjson�е�key
		JSONObject jsonObject = new JSONObject();
		jsonObject = jsonObject.fromObject(json);
		Iterator iterator = jsonObject.keys();
		System.out.println("--" + StringUtils.captureName(name) + "Model--");
		fileNameList.add(StringUtils.captureName(name) + "Model");

		// package�İ�
		String packageBuffer = String.format(A_T_0, packageName);
		strBuffer.append_feed(packageBuffer);
		// classͷ���İ�
		String classHeadBuffer = String.format(A_T_1,
				StringUtils.captureName(name));
		strBuffer.append_feed(classHeadBuffer);

		// ѭ��modelÿ������
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);
			// ����һ��ĳ���
			if (value.indexOf("{") == 0) {
				JSONObject obj = new JSONObject();
				obj = obj.fromObject(value);
				Iterator iterators = obj.keys();
				if (iterators.hasNext()) {
					keyList.add(key);
					valueList.add(value);
					// ��������model
					String OtherModelBuffer = String.format(A_T_2,
							StringUtils.captureName(key));
					strBuffer.append_feed(OtherModelBuffer);
				}
			} else if (value.indexOf("[") == 0 && value.indexOf("{") == 1) {// ����һ��list�ĳ���
				JSONArray arr = new JSONArray();
				arr = arr.fromObject(value);
				arrkeyList.add(key);
				arrvalueList.add(arr.get(0).toString());
				// List��model
				String ListModelBuffer = String.format(A_T_3,
						StringUtils.captureName(key), key);
				strBuffer.append_feed(ListModelBuffer);
			} else {
				// ���ೡ��Ĭ��ΪString
				String stringBuffer = String.format(A_T_4, key);
				strBuffer.append_feed(stringBuffer);
			}
		}
		// ����
		String endBuffer = A_T_5;
		strBuffer.append_feed(endBuffer);
		System.out.println(strBuffer.getStrBuffer().toString());
		list.add(strBuffer.getStrBuffer());

		// ������һ��ĳ���
		if (valueList.size() > 0) {
			for (int i = 0; i < valueList.size(); i++) {
				jsonToModel(keyList.get(i), valueList.get(i), list,
						fileNameList);
			}
		}

		// ��һ��list�ĳ���
		if (arrvalueList.size() > 0) {
			for (int i = 0; i < arrvalueList.size(); i++) {
				jsonToModel(arrkeyList.get(i), arrvalueList.get(i), list,
						fileNameList);
			}
		}

	}

	/**
	 * ����json ����IOSmodel
	 * 
	 * @param name
	 * @param json
	 */
	public void jsonToIOSModel(String name, String json,
			ArrayList<StringBuffer> iosList, ArrayList<String> iosFileNameList) {
		// ����json
		ArrayList<String> keyList = new ArrayList<>();
		ArrayList<String> valueList = new ArrayList<>();
		// list json
		ArrayList<String> arrkeyList = new ArrayList<>();
		ArrayList<String> arrvalueList = new ArrayList<>();
		// ������ļ�����
		StrBufferUtils strBuffer = new StrBufferUtils();

		// ��ȡjson�е�key
		JSONObject jsonObject = new JSONObject();
		jsonObject = jsonObject.fromObject(json);
		Iterator iterator = jsonObject.keys();
		System.out.println("--" + StringUtils.captureName(name) + "Model--");
		iosFileNameList.add(StringUtils.captureName(name) + "Model");

		// import �İ�
		String strImportBuffer = String.format(I_T_0,
				StringUtils.captureName(name), StringUtils.captureName(name));
		strBuffer.append_feed(strImportBuffer);
		// interface �İ�
		StrBufferUtils strBuffer2 = new StrBufferUtils();
		String strInterfaceBuffer = String.format(I_T_1,
				StringUtils.captureName(name));
		strBuffer2.append_feed(strInterfaceBuffer);

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = jsonObject.getString(key);

			// ����һ��ĳ���
			if (value.indexOf("{") == 0) {
				JSONObject obj = new JSONObject();
				obj = obj.fromObject(value);
				Iterator iterators = obj.keys();
				if (iterators.hasNext()) {
					keyList.add(key);
					valueList.add(value);
					// ����model
					String strModelBuffer = String.format(I_T_2,
							StringUtils.captureName(key), key);
					strBuffer2.append_feed(strModelBuffer);
				}
			} else if (value.indexOf("[") == 0 && value.indexOf("{") == 1) {// ����һ��list�ĳ���
				JSONArray arr = new JSONArray();
				arr = arr.fromObject(value);
				arrkeyList.add(key);
				arrvalueList.add(arr.get(0).toString());
				// ����listmodel
				String ListModelBuffer = String.format(I_T_3,
						StringUtils.captureName(key), key);
				strBuffer2.append_feed(ListModelBuffer);
			} else {// ���ೡ��Ĭ��ΪString
				String stringBuffer = String.format(I_T_4, key);
				strBuffer2.append_feed(stringBuffer);
			}

		}
		strBuffer2.append_feed(I_T_5);

		// ���������model
		StrBufferUtils strBuffer3 = new StrBufferUtils();
		if (keyList.size() > 0) {
			for (int i = 0; i < keyList.size(); i++) {
				// import �İ�
				String importBuffer = String.format(I_T_6,
						StringUtils.captureName(keyList.get(i)));
				strBuffer3.append_feed(importBuffer);
			}
		}
		if (arrkeyList.size() > 0) {
			for (int i = 0; i < arrkeyList.size(); i++) {
				// import �İ�
				String importBuffer = String.format(I_T_6,
						StringUtils.captureName(arrkeyList.get(i)));
				strBuffer3.append_feed(importBuffer);
			}
		}

		strBuffer.append_feed(strBuffer3.getStrBuffer().toString());
		strBuffer.append_feed(strBuffer2.getStrBuffer().toString());
		System.out.println(strBuffer.getStrBuffer().toString());
		iosList.add(strBuffer.getStrBuffer());

		// ������һ��ĳ���
		if (valueList.size() > 0) {
			for (int i = 0; i < valueList.size(); i++) {
				jsonToIOSModel(keyList.get(i), valueList.get(i), iosList,
						iosFileNameList);
			}
		}

		// ��һ��list�ĳ���
		if (arrvalueList.size() > 0) {
			for (int i = 0; i < arrvalueList.size(); i++) {
				jsonToIOSModel(arrkeyList.get(i), arrvalueList.get(i), iosList,
						iosFileNameList);
			}
		}

	}

	/**
	 * jsonת��reform
	 */
	public void jsonToReformer(String name) {
		// ������ļ�����
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("package " + packageName + ".reform;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("/**");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(" * ");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(" */");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("public class " + StringUtils.captureName(name)
				+ "Reformer extends BaseReformer {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("    public " + StringUtils.captureName(name)
				+ "Model " + name + "List;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));
		FilesUtils.makeStr(strBuffer.toString(), name + "Reformer.java",
				reformPathStr);
	}

	/**
	 * jsonת��reformerImpl
	 */
	public void jsonToReformerImpl(String name) {

		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("package " + packageName + ".reformerImpl;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("/**");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(" * ");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(" */");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("public class " + name
				+ "ReformerImpl implements ReformerInterface {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("@Override");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("public String getURL(EnumConstant.FitUrl fitUrl) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("return null;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));

		strBuffer.append("@Override");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("public ReformerInterface getReformerInterface(EnumConstant.FitUrl fitUrl) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("return null;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));

		strBuffer.append("@Override");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("public BaseReformer dataToReformer(String s, String s1, boolean b) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("Gson gson = FitGsonFactory.create();");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(StringUtils.captureName(name)
				+ "Model data = gson.fromJson(s1, "
				+ StringUtils.captureName(name) + "Model.class);");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("return dataToReformer(s, data, false);");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));

		strBuffer.append("@Override");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("public BaseReformer dataToReformer(String s, BaseData baseData, boolean b) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append(StringUtils.captureName(name)
				+ "Reformer reformer = new " + StringUtils.captureName(name)
				+ "Reformer();");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("reformer." + name + "List = ("
				+ StringUtils.captureName(name) + "Model) baseData;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("return reformer;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));
		FilesUtils.makeStr(strBuffer.toString(), name + "ReformerImpl.java",
				reformImplPathStr);
	}

	/**
	 * ��ȡrequest����
	 */
	public void jsonToPresenterImpl(String name) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("package " + packageName + ".presenterimpl;");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("public void " + name
				+ "(Context context, RequestModel requestModel) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("try {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("ReformerImpl reformerInterface = new ReformerImpl(new "
						+ StringUtils.captureName(name) + "Refermer());");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("String strUrl = new ReformerImpl().getURL(EnumConstant.FitUrl."
						+ StringUtils.captureName(name) + ");");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer
				.append("api.getHttp(strUrl, context, uiListener, reformerInterface, requestModel);");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("} catch (Exception e) {");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("FitAction.upload_e(\"PresenterImpl."
				+ StringUtils.captureName(name) + "Success\", e);");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));
		strBuffer.append("}");
		strBuffer.append(System.getProperty("line.separator"));
		FilesUtils.makeStr(strBuffer.toString(), name + "PresenterImpl.java",
				presenterImplPathStr);
	}

}
