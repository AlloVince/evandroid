package resthttp;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Policy {

    /**
     * tags : []
     * count : 513
     * range : 1
     * status : 1
     * isNew : true
     * return : {"farReturn":0,"weeklyReturn":0,"dailyReturn":0,"monthlyReturn":0}
     * type : 1
     * stocks : [{"position":"25","sort":"1","minHoldTime":null,"symbol":"SZ000952","stockName":"广济药业","restrationRatio":null,"isToday":false,"maxHoldTime":null,"startPrice":"13.33","restrationAt":"0","targetPrice":null,"updatedAt":"1440331938","id":"2687","isDelete":"0","market":"SZ","suggestPrice":null,"policyId":"1318","createdAt":"1440331938","stopLossPrice":null,"stockId":"000952","returns":{"farReturn":"0.00","weeklyReturn":"0.00","dailyReturn":"0.00","monthlyReturn":"0.00"}},{"position":"25","sort":"2","minHoldTime":null,"symbol":"SZ300030","stockName":"阳普医疗","restrationRatio":null,"isToday":false,"maxHoldTime":null,"startPrice":"18.82","restrationAt":"0","targetPrice":null,"updatedAt":"1440331938","id":"2688","isDelete":"0","market":"SZ","suggestPrice":null,"policyId":"1318","createdAt":"1440331938","stopLossPrice":null,"stockId":"300030","returns":{"farReturn":"0.00","weeklyReturn":"0.00","dailyReturn":"0.00","monthlyReturn":"0.00"}},{"position":"25","sort":"3","minHoldTime":null,"symbol":"SZ000425","stockName":"徐工机械","restrationRatio":null,"isToday":false,"maxHoldTime":null,"startPrice":"10.07","restrationAt":"0","targetPrice":null,"updatedAt":"1440331938","id":"2689","isDelete":"0","market":"SZ","suggestPrice":null,"policyId":"1318","createdAt":"1440331938","stopLossPrice":null,"stockId":"000425","returns":{"farReturn":"0.00","weeklyReturn":"0.00","dailyReturn":"0.00","monthlyReturn":"0.00"}},{"position":"25","sort":"4","minHoldTime":null,"symbol":"SZ002466","stockName":"天齐锂业","restrationRatio":null,"isToday":false,"maxHoldTime":null,"startPrice":"60.9","restrationAt":"0","targetPrice":null,"updatedAt":"1440331938","id":"2690","isDelete":"0","market":"SZ","suggestPrice":null,"policyId":"1318","createdAt":"1440331938","stopLossPrice":null,"stockId":"002466","returns":{"farReturn":"0.00","weeklyReturn":"0.00","dailyReturn":"0.00","monthlyReturn":"0.00"}}]
     * id : 1318
     * content : <p><span></span></p><p><span><strong>要点</strong></span></p><p><span>1）三季报一点点的多了，不过虽然有些公司是报预增的，但是前三季度增速却比中期增速小····这只能说明三季度经营不行啊；</span></p><p><span>2）公司周末披露自己是证金公司概念股的消息好少，看来不争宠了呀，是市场要换风格了嘛？</span></p><p><span><br></span></p><p><strong><span>重要</span><span style="line-height: 1;">公告整理</span></strong></p><p><strong style="line-height: 1;">1.中报送转</strong></p><p><strong>众信旅游（002707）</strong>：中报拟10转10，前三季度预增50-90%（公司主营业务增长，及对竹园国旅非同一控制下企业合并带来的利润增长）；</p><p><strong>美盛文化（002699）</strong>：上半年净利2643万，同比增逾5成；前三季度预增20-50%；</p><p><strong>中国武夷（000797）</strong>：上半年净利7156万，同比增逾7成；</p><p><strong>智慧松德（300173）</strong>：上半年净利暴增660%；</p><p><strong>金风科技（002202）</strong>：上半年净利12.45亿，同比增276%（风电快速增长ing）；</p><p><strong>恒生电子（600570）</strong>：中期净利2亿，增长85%（很难再爱你啊）；</p><p><strong>张江高科（600895）</strong>：中期净利增长171%（科创龙头，怎么老是没人爱呢）；</p><p><strong>吉林敖东（000623）</strong>：半年净利增逾2倍（广发证券贡献近15亿投资收益，已经很清楚了吧）；</p><p><strong>长青集团（002616）</strong>：上半年净利5003万，同比增逾2倍；前三季度同比增长30-60%；</p><p><strong>广发证券（000776）</strong>：半年报净利润大增401.85%；</p><p><strong>春秋航空（601021）</strong>：中期拟每10股转增10股，中期增长128.81%；</p><p><strong>国泰君安（601211）</strong>：中期拟10派1，中期同比增长348.22%；</p><p><strong>徐工机械（000425）</strong>：中期10股转20股；</p><p><strong>龙马环卫（603686）</strong>：中期净利增30%，拟10转10；</p><p><strong>汇冠股份（300282）</strong>：上半年净利润大增7.6倍；</p><p><strong>天齐锂业（002466）</strong>：上半年净利同比大增135%；</p><p><strong>易尚展示（002751）</strong>：中期拟10转10；</p><p><span><strong>2.扩产转型</strong></span></p><p><strong>辉煌科技（002296）</strong>：拟控股Comlab拓展产品链条，24日复牌（转型轨道交通信号）；</p><p><strong>国光电器（002045）</strong>：拟募资7亿加码主业；</p><p><strong>诺普信（002215）</strong>：拟出资1750万元共同设立P2P平台公司；</p><p><strong>聚光科技（300203）</strong>：拟收购安谱实验45.53%股权，布局“仪器＋耗材”（我是科学家）；</p><p><strong>阳普医疗（300030）</strong>：拟5亿元投资医疗健康产业园；</p><p><strong>中材科技（002080）</strong>：定增近32亿引入金发科技，控股股东注入玻璃纤维业务（好大的规模）；</p><p><strong>汉威电子（300007）</strong>：定增13亿拓展智慧市政；</p><p><strong>华东数控（002248）</strong>：内蒙古久泰拟作价66亿元借壳华东数控（只能涨了）；</p><p><strong>金贵银业（002716）</strong>：8亿并购金和矿业；</p><p><strong>河北钢铁（000709）</strong>：定增募资80亿，41亿收购汽车板公司；</p><p><strong>广济药业（000952）</strong>：定增5.5亿元补血，建设生物产业园；</p><p><br><span></span></p><p><span><strong>——公告君为你整理好了，懒得看下去就到此为止——</strong></span></p><p><span><br></span></p><p><span><strong>中报送转公告</strong></span></p><p><strong>易尚展示(002751)</strong>8月23日晚间公告披露2015年半年度利润分配预案：拟以截至2015年6月30日公司总股本7024万股为基数，进行资本公积金转增股本，向全体股东每10股转增10股，共计转增7,024万股，不送股，不派发现金股利，转增后公司总股本将增至14,048万股。</p><p><span><br></span></p><p><strong>新开源（300109）</strong>8月23日晚间发布了2015年半年度报告。2015年上半年，公司实现营业收入1.27亿元，比上年同期增长1.05%；实现归属于母公司的净利润2587.15万元，比上年同期增长84.26%；每股收益0.22元，比上年同期增长83.33%。</p><p><span><br></span></p><p><strong>天齐锂业(002466)</strong>8月23日晚间披露半年报，报告期内公司实现营业收入7.55亿元，同比增长16%；实现净利润4097万元，同比增长135%。</p><p><span><br></span></p><p><strong>福日电子（600203）</strong>8月23日晚间发布2015 年半年度报告，公司实现营业收入30.95亿元，同比增长140.12%；实现归属于上市公司股东的净利润2362.4万元，同比增长302.77%。</p><p><span><br></span></p><p><strong>汇冠股份（300282）</strong>8月23日晚间发布了2015年半年度报告。2015年上半年，公司实现营业收入70,600万元，同比增加570.25%，归属于上市公司普通股股东的净利润2,575万元，同比增加761.75%。</p><p><span><br></span></p><p><strong>龙马环卫（603686）</strong>8月23日晚间发布2015 年半年度报告，公司实现营业收入7.6亿元，同比增长36.24%；实现归属于上市公司股东的净利润8063.85万元，同比增长30.73%。公司拟向全体股东每10股转增10股。</p><p><span><br></span></p><p><strong>徐工机械（000425）</strong>披露，控股股东徐工集团提交议案，拟以资本公积金向全体股东每10股转增20股，半年度不送红股、不进行现金分红。</p><p><span><br></span></p><p><strong>春秋航空（601021）</strong>中报显示，上半年营收39.5亿元，同比增长15.04%；归属上市公司股东净利6.2亿元，同比增长128.81%；中期拟每10股转增10股。</p><p><span><br></span></p><p><strong>国泰君安（601211）</strong>中报显示，上半年营收223亿元，同比增长272.20%；归属上市公司股东净利96.4亿元，同比增长348.22%；中期拟每10股派发现金股利1元（含税）。</p><p><span><br></span></p><p><strong>华泰证券（601688）</strong>于8月21日晚披露2015年中报。今年上半年实现归属于母公司股东的净利润66.75亿元，同比增长339.82%；基本每股收益1.145元，较上年同期增长322.51%。公司董事会在审议半年报是，未拟定利润分配预案、资本公积金转赠股本预案。</p><p><span><br></span></p><p><strong>众信旅游（002707）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入31.36亿元，同比增长87.58%；实现净利润5859.99万元，同比增长63.30%；每股收益0.30元。此外，公司拟以资本公积金向全体股东每10股转增10股。公司同时预计2015年1-9月归属于上市公司股东的净利润变动区间为1.40亿元-1.77亿元，同比增长50%-90%。公司表示，业绩增长原因在于，公司主营业务增长，及对竹园国旅非同一控制下企业合并带来的利润增长。</p><p><span><br></span></p><p><strong>广发证券（000776）</strong>8月21日晚间披露的中报显示，该公司上半年实现营业收入197.12亿元，同比增长325.38%；归属于上市公司股东的净利润84.06亿元，同比增长401.85%；基本每股收益1.11元，较上年同期增长362.5%。</p><p><span><br></span></p><p><strong>长青集团（002616）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入7.22亿元，同比增长22.92%；实现净利润5002.85万元，同比增长209.54%。此外，公司预计2015年1-9月归属于上市公司股东的净利润变动区间为7334.96万元-9027.65万元，同比增长30%-60%。</p><p><span><br></span></p><p><strong>吉林敖东（000623）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入11.39亿元，比上年同期增长0.38%；净利润16.60亿元，同比增长206.95%；基本每股收益1.86元。其中，公司对广发证券的投资收益为14.91亿元，同比增长323.35%。</p><p><span><br></span></p><p><strong>美盛文化（002699）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入1.46亿元，同比下降11.78%；实现净利润2643.37万元，同比增长53.93%。此外，公司预计2015年1-9月净利润变动区间为7485.64万元-9357.05万元，同比增长20%-50%。</p><p><span><br></span></p><p><strong>中国武夷（000797）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入10.02亿元，同比增长1.25%；实现净利润7155.81万元,同比增长72.72%。</p><p><span><br></span></p><p><strong>智慧松德（300173）</strong>8月21日晚间发布半年度报告。今年上半年，公司实现营业总收入2.30亿元，较上年同期增长84.21%；实现营业利润1830.94万元，较上年同期增长407.54%；实现利润总额2574.55万元，较上年同期增长653.55%；实现归属于上市公司股东的净利润2286.43万元，较上年同期增长660.26%。</p><p><span><br></span></p><p><strong>华电国际（600027）</strong>8月21日晚间披露2015年半年报，报告期内，公司实现营业总收入305.81亿元，比去年同期减少约9.48%，主要原因是发电量同比下降及上网电价下调的综合影响；归属于母公司所有者的净利润35.05亿元，比去年同期增加30.89%，主要原因是燃料价格下跌的影响；基本每股收益为0.4元。</p><p><span><br></span></p><p><strong>金风科技（002202）</strong>8月21日晚间披露了半年报，报告期公司实现营业收入94.29亿元，同比增长110.90%；实现净利润12.45亿元，同比增长276.62%；基本每股收益0.46元。</p><p><span><br></span></p><p><strong>恒生电子（600570）</strong>8月21日晚间披露2015年半年报：实现营业收入9.1亿元，同比增长96.34%；归属于上市公司股东的净利润2.1亿元，同比增长85.22%；基本每股收益0.34元。</p><p><span><br></span></p><p><strong>张江高科（600895）</strong>8月21日晚间披露2015年半年报，报告期内，公司实现营业收入5.1亿元，同比下降30.11%；归属于上市公司股东的净利润2.84亿元，同比增长170.93%；基本每股收益0.18元。</p><p><span><br></span></p><p><span><strong>定增重组公告</strong></span></p><p><strong>国光电器（002045）</strong>8月21日晚间发布公告，公司拟非公开发行股票不超过 80,459,669 股，募资不超过 70,000万元用于扩产主业扬声器产品和偿还银行贷款。其中，员工持股计划将参与认购。公司股票 8 月 24 日（星期一）开市起复牌 。</p><p><span><br></span></p><p><strong>聚光科技（300203）</strong>8月21日晚间发布公告。公司拟以非公开发行募集资金 1.49亿元受让夏敏勇、江平合计持有的安谱实验45.53%的股权，并以募集资金 8184.23万元对安谱实验增资。</p><p><span><br></span></p><p><strong>汉威电子（300007）</strong>8月21日晚间发布公告，公司拟非公开发行股票数量不超过 3,700 万股，募集资金总额不超过 132,700.00 万元,用于智慧水务、热力、市政综合管理系统项目和上海运营及研发中心以及补充流动资金。</p><p><span><br></span></p><p><strong>华东数控（002248）</strong>23日公告，以8.87元/股的价格，向内蒙古久泰全体股东发行66亿元股份，购买其持有的内蒙古久泰100%股权；以11.77元/股的价格，定增募资不超30亿元。次交易完成后，公司在现有金属切削机床业务基础上将增加现代煤化工业务，山东久泰将成控股股东。</p><p><span><br></span></p><p><strong>金字火腿(002515)</strong>8月23日晚间公告，拟以自有资金对福建盈科创业投资有限公司增资3亿元。同时，盈科投资的股东钱明飞和韩福军分别将其持有的部分出资额以合计7200万的价款转让给公司。交易完成后，金字火腿将持有盈科投资24.8%的股权。公司股票将于24日复牌。</p><p><span><br></span></p><p><strong>广济药业（000952）</strong>8月23日晚间发布非公开发行股票预案，公司拟以11.20 元/股的价格向大股东省长投集团及其控制的长江并购基金非公开发行不超过 4910.71万股，募集资金总额不超过 5.5亿元。公司股票将于8月24日复牌。</p><p><span><br></span></p><p><strong>河北钢铁（000709）</strong>8月23日晚间发布非公开发行股票预案，公司拟以5.60元/股的价格向10名特定对象发行不超过 14.29亿股，募集资金总额不超过 80亿元，公司股票将于8月24日复牌。</p><p><span><br></span></p><p><strong>金贵银业（002716）</strong>22日发布公告，公司股票将于8月24日正式复牌交易。停牌期间，金贵银业在产业链条两端动作不断。一方面拟斥资8.26亿元，以股票加现金的方式收购西藏金和矿业有限公司（下称：金和矿业）100%股权，夯实上游原材料储备；另一方面，金贵银业 “液态渣直接还原节能改造与余热发电项目”在经过半年的试生产之后，于2015年7月26日正式建成投产。</p><p><span><br></span></p><p><span><strong>投资合作公告</strong></span></p><p><strong>电广传媒（000917）</strong>8月23日晚间发布公告，公司拟与感知科技有限公司共同出资成立上海感知实业发展有限公司，合资公司注册资本为2亿元，电广传媒以现金出资1亿元。</p><p><span><br></span></p><p><strong>辉煌科技（002296）</strong>8月21日晚间公告，公司与COMLAB(北京)通信系统设备有限公司（简称“Comlab”）的股东签署了意向性协议。公司拟以现金方式收购标的公司不少于51%的股权，其中收购Comlab。AG持有的不少于26%的股权、收购上海瑞盾网络科技发展有限公司持有的不少于25%的股权。本次交易涉及金额约为1.5亿元-2.1亿元，视具体收购的股权比例而定。</p><p><span><br></span></p><p><strong>诺普信（002215）</strong>8月21日晚间公告，为满足业务拓展和战略发展的需要，公司拟以自有资金1750万元与融信南方和陈楚芹共同出资设立深圳***金融服务有限公司（名称暂未定，简称“金融公司”），公司占金融公司的股权比例为35%。其中，公司实际控制人卢柏强和卢丽红分别持有融信南方94%、6%的股权。</p><p><span><br></span></p><p><strong>阳普医疗（300030）</strong>8月21日晚间发布公告称，公司近日与湖南郴州经济开发区管理委员会签署了《招商意向书》，公司拟投资不超过人民币 5 亿元建设医疗健康产业园。项目地址位于长冲创新创业园规划范围内，北临高新路、西临新园路、南邻高科路，面积约300亩（具体使用面积以相关部门最终测量的面积为准）。建设医疗健康产业园有利于公司在医疗健康领域核心技术能力的形成，确保战略产品的生产能力提升；同时为公司开拓郴州市场，布局公司医疗健康产业平台提供便利。</p><p><span><br></span></p><p><strong>中材科技（002080）</strong>8月21日晚间发布公告，公司拟作价385,045.84 万元收购控股股东持有的泰山玻纤 100%的股权，并定增募资318,081.89 万元，用于标的公司生产线建设以及补充上市公司流动资金，其中，公司股权投资计划和金风科技（002202）将参与认购。</p><p><br></p><p><span><strong>增持回购</strong></span></p><p><strong>泰豪科技（600590）</strong>8月21日晚间公告，公司拟实施员工持股份计划。参加计划的员工总人数不超过200人，其中公司部分董事、高级管理人员认购份额占员工持股计划的总份额比例约20%，员工持股计划筹集资金总额上限为1亿元，分为1亿份份额。每份份额为1元。</p><p><span><br></span></p><p><strong>香雪制药（300147）</strong>披露，截至2015年8月15日，汇金公司及中证金融资产管理计划合计持有公司3985.15万股股票,占公司全部股份的6.01%。</p><p><br></p><p><span><strong>其他公告</strong></span></p><p><strong>四川长虹（600839）</strong>8月21日晚间公告，公司原计划收购四川电子军工集团有限公司持有的零八一集团 100%股权，但因为军工集团按照相关规定及要求需进场挂牌出让零八一集团 100%股权，公司拟通过自有资金按照企业国有产权管理有关规定参与进场竞价收购零八一集团股权。同时，公司股票将于8月24日起复牌。</p><p><span><br></span></p><p><strong>大连三垒(002621)</strong>8月23日晚间公告，为使公司证券简称更能全面、准确地反映公司经营的行业特点，便于公众认知，同时考虑更能体现未来公司的多元化发展，因此，公司决定将中文证券简称由"大连三垒"变更为"三垒股份"，英文证券简称由"Dalian Sunlight"变更为"Sunlight Co.,Ltd"。</p><p><br></p>
     * updatedAt : 1440331938
     * additions : {"total":0}
     * title : 阳普医疗、广济药业：产业园升级，鸟枪换炮，气势不一样了
     * duration : shortTerm
     * level : 0
     * importance : 1
     * share_url : http://xgb.wallstreetcn.com/strategy/1318
     * createdAt : 1440331938
     * userId : 674673
     * user : {"id":"674673","lastName":"公告说","returns":null,"avatar":"http://avatar.cdn.wallstcn.com/c8/a9/d3/9e6cEh.png","firstName":"","profile":{"bio":"","company":"利润预增、定向增发等确定性机会","jobTitle":""}}
     * thumb : null
     */
    private List<?> tags;
    private int count;
    private String range;
    private String status;
    private boolean isNew;
    @SerializedName("return")
    private ReturnXEntity returnX;
    private String type;
    private List<StocksEntity> stocks;
    private String id;
    private String content;
    private String updatedAt;
    private AdditionsEntity additions;
    private String title;
    private String duration;
    private String level;
    private String importance;
    private String share_url;
    private String createdAt;
    private String userId;
    private UserEntity user;
    private String thumb;

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public void setReturnX(ReturnXEntity returnX) {
        this.returnX = returnX;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStocks(List<StocksEntity> stocks) {
        this.stocks = stocks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAdditions(AdditionsEntity additions) {
        this.additions = additions;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public List<?> getTags() {
        return tags;
    }

    public int getCount() {
        return count;
    }

    public String getRange() {
        return range;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public ReturnXEntity getReturnX() {
        return returnX;
    }

    public String getType() {
        return type;
    }

    public List<StocksEntity> getStocks() {
        return stocks;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public AdditionsEntity getAdditions() {
        return additions;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getLevel() {
        return level;
    }

    public String getImportance() {
        return importance;
    }

    public String getShare_url() {
        return share_url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getThumb() {
        return thumb;
    }

    public static class ReturnXEntity {
        /**
         * farReturn : 0
         * weeklyReturn : 0
         * dailyReturn : 0
         * monthlyReturn : 0
         */
        private int farReturn;
        private int weeklyReturn;
        private int dailyReturn;
        private int monthlyReturn;

        public void setFarReturn(int farReturn) {
            this.farReturn = farReturn;
        }

        public void setWeeklyReturn(int weeklyReturn) {
            this.weeklyReturn = weeklyReturn;
        }

        public void setDailyReturn(int dailyReturn) {
            this.dailyReturn = dailyReturn;
        }

        public void setMonthlyReturn(int monthlyReturn) {
            this.monthlyReturn = monthlyReturn;
        }

        public int getFarReturn() {
            return farReturn;
        }

        public int getWeeklyReturn() {
            return weeklyReturn;
        }

        public int getDailyReturn() {
            return dailyReturn;
        }

        public int getMonthlyReturn() {
            return monthlyReturn;
        }
    }

    public static class StocksEntity {
        /**
         * position : 25
         * sort : 1
         * minHoldTime : null
         * symbol : SZ000952
         * stockName : 广济药业
         * restrationRatio : null
         * isToday : false
         * maxHoldTime : null
         * startPrice : 13.33
         * restrationAt : 0
         * targetPrice : null
         * updatedAt : 1440331938
         * id : 2687
         * isDelete : 0
         * market : SZ
         * suggestPrice : null
         * policyId : 1318
         * createdAt : 1440331938
         * stopLossPrice : null
         * stockId : 000952
         * returns : {"farReturn":"0.00","weeklyReturn":"0.00","dailyReturn":"0.00","monthlyReturn":"0.00"}
         */
        private String position;
        private String sort;
        private String minHoldTime;
        private String symbol;
        private String stockName;
        private String restrationRatio;
        private boolean isToday;
        private String maxHoldTime;
        private String startPrice;
        private String restrationAt;
        private String targetPrice;
        private String updatedAt;
        private String id;
        private String isDelete;
        private String market;
        private String suggestPrice;
        private String policyId;
        private String createdAt;
        private String stopLossPrice;
        private String stockId;
        private ReturnsEntity returns;

        public void setPosition(String position) {
            this.position = position;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setMinHoldTime(String minHoldTime) {
            this.minHoldTime = minHoldTime;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public void setRestrationRatio(String restrationRatio) {
            this.restrationRatio = restrationRatio;
        }

        public void setIsToday(boolean isToday) {
            this.isToday = isToday;
        }

        public void setMaxHoldTime(String maxHoldTime) {
            this.maxHoldTime = maxHoldTime;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

        public void setRestrationAt(String restrationAt) {
            this.restrationAt = restrationAt;
        }

        public void setTargetPrice(String targetPrice) {
            this.targetPrice = targetPrice;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public void setSuggestPrice(String suggestPrice) {
            this.suggestPrice = suggestPrice;
        }

        public void setPolicyId(String policyId) {
            this.policyId = policyId;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setStopLossPrice(String stopLossPrice) {
            this.stopLossPrice = stopLossPrice;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public void setReturns(ReturnsEntity returns) {
            this.returns = returns;
        }

        public String getPosition() {
            return position;
        }

        public String getSort() {
            return sort;
        }

        public String getMinHoldTime() {
            return minHoldTime;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getStockName() {
            return stockName;
        }

        public String getRestrationRatio() {
            return restrationRatio;
        }

        public boolean isIsToday() {
            return isToday;
        }

        public String getMaxHoldTime() {
            return maxHoldTime;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public String getRestrationAt() {
            return restrationAt;
        }

        public String getTargetPrice() {
            return targetPrice;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getId() {
            return id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public String getMarket() {
            return market;
        }

        public String getSuggestPrice() {
            return suggestPrice;
        }

        public String getPolicyId() {
            return policyId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getStopLossPrice() {
            return stopLossPrice;
        }

        public String getStockId() {
            return stockId;
        }

        public ReturnsEntity getReturns() {
            return returns;
        }

        public static class ReturnsEntity {
            /**
             * farReturn : 0.00
             * weeklyReturn : 0.00
             * dailyReturn : 0.00
             * monthlyReturn : 0.00
             */
            private String farReturn;
            private String weeklyReturn;
            private String dailyReturn;
            private String monthlyReturn;

            public void setFarReturn(String farReturn) {
                this.farReturn = farReturn;
            }

            public void setWeeklyReturn(String weeklyReturn) {
                this.weeklyReturn = weeklyReturn;
            }

            public void setDailyReturn(String dailyReturn) {
                this.dailyReturn = dailyReturn;
            }

            public void setMonthlyReturn(String monthlyReturn) {
                this.monthlyReturn = monthlyReturn;
            }

            public String getFarReturn() {
                return farReturn;
            }

            public String getWeeklyReturn() {
                return weeklyReturn;
            }

            public String getDailyReturn() {
                return dailyReturn;
            }

            public String getMonthlyReturn() {
                return monthlyReturn;
            }
        }
    }

    public static class AdditionsEntity {
        /**
         * total : 0
         */
        private int total;

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }
    }

    public static class UserEntity {
        /**
         * id : 674673
         * lastName : 公告说
         * returns : null
         * avatar : http://avatar.cdn.wallstcn.com/c8/a9/d3/9e6cEh.png
         * firstName :
         * profile : {"bio":"","company":"利润预增、定向增发等确定性机会","jobTitle":""}
         */
        private String id;
        private String lastName;
        private String returns;
        private String avatar;
        private String firstName;
        private ProfileEntity profile;

        public void setId(String id) {
            this.id = id;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setReturns(String returns) {
            this.returns = returns;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setProfile(ProfileEntity profile) {
            this.profile = profile;
        }

        public String getId() {
            return id;
        }

        public String getLastName() {
            return lastName;
        }

        public String getReturns() {
            return returns;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getFirstName() {
            return firstName;
        }

        public ProfileEntity getProfile() {
            return profile;
        }

        public static class ProfileEntity {
            /**
             * bio :
             * company : 利润预增、定向增发等确定性机会
             * jobTitle :
             */
            private String bio;
            private String company;
            private String jobTitle;

            public void setBio(String bio) {
                this.bio = bio;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getBio() {
                return bio;
            }

            public String getCompany() {
                return company;
            }

            public String getJobTitle() {
                return jobTitle;
            }
        }
    }
}
