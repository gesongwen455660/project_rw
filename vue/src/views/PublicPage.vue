<template >
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height:100%">
      <el-menu
          :default-openeds="['1']"
      >
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>试运行</template>  <!--导航1-->
          <el-menu-item-group>
            <el-menu-item index="all" @click="selectProductType('')">全部产品</el-menu-item>
          </el-menu-item-group>
          <!--          <el-submenu index="1-4">-->
          <!--            <template slot="title">选项4</template>-->
          <!--            <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
          <!--          </el-submenu>-->
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>订单</template> <!--导航2-->
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="订单(demo)" @click="orderDemo">订单(demo)</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>供应商</template> <!--导航3-->
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header  style=" font-size: 12px">
        <el-dropdown style="float: right">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
          <span>gsw</span>
        </el-dropdown>
<p style="font-size: 40px"> </p>
      </el-header>

      <el-main>
        <template>
          <div class="container">
            <div class="select-container">
              <select class="selectOption1" v-model="productType">
                <option value="">全部产品</option>
                <option value="制冰机">制冰机</option>
                <option value="火炉">火炉</option>
                <option value="冰箱">冰箱</option>
                <option value="酒柜">酒柜</option>
              </select>
            </div>
            <div class="input-container">
              <el-input type="text" style="width: 300px" placeholder="请按sku查询" v-model="sku" @keyup.native.enter="load"></el-input>
              <el-button type="primary" @click="load">搜索</el-button>
            </div>
            <div class="input-container">
              <el-input type="text" style="width: 300px" placeholder="请按工厂查询" v-model="factory" @keyup.native.enter="load"></el-input>
              <el-button type="primary" @click="load">搜索</el-button>
            </div>
            <div>
              <el-button @click="toggleFields">
                {{ showAllFields ? '隐藏字段' : '显示全部字段' }}
              </el-button>
            </div>
          </div>
        </template>

        <div >
          <el-table :data="moreProductData" :key="showAllFields" class="custom-table dragscroll" :scroll-x="true">
            <el-table-column width="100px"  prop="sku" label="sku"></el-table-column>



            <el-table-column width="250px" label="图片" prop="url">
              <template slot-scope="scope">
                <el-popover placement="top-start" title="" trigger="click">
                  <img :src=getUrl(scope.row,1) alt="" style="width: 400px;height: 400px">
                  <img :src=getUrl(scope.row,2) alt="" style="width: 400px;height: 400px">
                  <img :src=getUrl(scope.row,3) alt="" style="width: 400px;height: 400px">
                  <img slot="reference" :src=getUrl(scope.row,1) style="width: 150px;height: 150px">
                </el-popover>
              </template>
            </el-table-column>


            <el-table-column width="100px" prop="factory" label="工厂"></el-table-column>

            <el-table-column width="150px" prop="fba" label="产品属性FBA"></el-table-column>
            <el-table-column width="150px" prop="fbm" label="产品属性FBM"></el-table-column>
            <el-table-column width="150px" prop="productlenghcm1" label="产品尺寸CM 最长"></el-table-column>
            <el-table-column width="150px" prop="productlenghcm2" label="产品尺寸CM 次长"></el-table-column>
            <el-table-column width="150px" prop="productshortlenghcm" label="产品尺寸CM 最短"></el-table-column>
            <el-table-column width="150px" prop="fulfillmentfeeamazon" label="亚马逊预估快递费"></el-table-column>
            <el-table-column width="150px" prop="fulfillmentfeeoutsea" label="海外仓预估快递费(基础运费+附加费)"></el-table-column>
            <el-table-column width="150px" prop="outboxlenghcm1" label="外箱尺寸CM 最长"></el-table-column>
            <el-table-column width="150px" prop="outboxlenghcm2" label="外箱尺寸CM 次长"></el-table-column>
            <el-table-column width="150px" prop="outboxshortlenghcm" label="外箱尺寸CM 最短"></el-table-column>
            <el-table-column width="200px" prop="weightkg1" label="整箱重量KG 净重"></el-table-column>
            <el-table-column width="200px" prop="weightkg2" label="整箱重量KG 毛重"></el-table-column>
            <el-table-column width="200px" prop="productlenghinch1" label="产品尺寸INCH 最长"></el-table-column>
            <el-table-column width="200px" prop="productlenghinch2" label="产品尺寸INCH 次长"></el-table-column>
            <el-table-column width="200px" prop="productshortlenghinch" label="产品尺寸INCH 最短"></el-table-column>
            <el-table-column width="200px" prop="outboxlenghinch1" label="外箱尺寸INCH 最长"></el-table-column>
            <el-table-column width="200px" prop="outboxlenghinch2" label="外箱尺寸INCH 次长"></el-table-column>
            <el-table-column width="200px" prop="outboxshortlenghinch" label="外箱尺寸INCH 最短"></el-table-column>
            <el-table-column width="200px" prop="weightlb1" label="整箱重量LB 净重"></el-table-column>
            <el-table-column width="200px" prop="weightlb2" label="整箱重量LB 毛重"></el-table-column>
            <el-table-column width="200px" prop="quantityhq" label="装柜量"></el-table-column>
            <el-table-column width="200px" prop="quantity" label="一箱几件"></el-table-column>
            <el-table-column width="200px" prop="inboxlenghinch1" label="一箱多件内箱尺寸INCH 最长"></el-table-column>
            <el-table-column width="200px" prop="inboxlenghinch2" label="一箱多件内箱尺寸INCH 次长"></el-table-column>
            <el-table-column width="200px" prop="inboxshortlenghinch" label="一箱多件内箱尺寸INCH 最短"></el-table-column>
            <el-table-column width="200px" prop="inboxweightlb1" label="一箱多件内箱重量LB 净重"></el-table-column>
            <el-table-column width="200px" prop="inboxweightlb2" label="一箱多件内箱重量LB 毛重"></el-table-column>
            <el-table-column width="200px" prop="inboxlenghcm1" label="一箱多件内箱尺寸CM 最长"></el-table-column>
            <el-table-column width="200px" prop="inboxlenghcm2" label="一箱多件内箱尺寸CM 次长"></el-table-column>
            <el-table-column width="200px" prop="inboxshortlenghcm" label="一箱多件内箱尺寸CM 最短"></el-table-column>
            <el-table-column width="200px" prop="inboxweightkg1" label="一箱多件内箱重量KG 净重"></el-table-column>
            <el-table-column width="200px" prop="inboxweightkg2" label="一箱多件内箱重量KG 毛重"></el-table-column>
            <el-table-column width="200px" prop="minlenghinchheight" label="最小包装尺寸INCH 最小包装尺寸长"></el-table-column>
            <el-table-column width="200px" prop="minlenghinchwidth" label="最小包装尺寸INCH 最小包装尺寸宽"></el-table-column>
            <el-table-column width="200px" prop="minlenghinchhigh" label="最小包装尺寸INCH 最小包装尺寸高"></el-table-column>
            <el-table-column width="200px" prop="minweight1" label="最小包装尺寸净重"></el-table-column>
            <el-table-column width="200px" prop="minweight2" label="最小包装尺寸毛重"></el-table-column>
            <el-table-column width="200px" prop="type" label="产品类型"></el-table-column>
            <el-table-column  width="150px" label="最后更新时间">
              <template slot-scope="scope">
                <el-button @click="showUpdateHistory(scope.row.id)">查看更新日志</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <div>
          <el-dialog  width="100%" title="更新日志" :visible="showUpdateDialog" @close="closeUpdateDialog">
            <el-collapse >
              <el-table :data="productLogData" class="custom-table">
                <el-table-column width="200px" prop="factory" label="工厂"></el-table-column>
                <el-table-column width="200px" prop="sku" label="款号"></el-table-column>
                <el-table-column width="250px" prop="fba" label="产品属性FBA"></el-table-column>
                <el-table-column width="200px" prop="fbm" label="产品属性FBM"></el-table-column>
                <el-table-column width="150px" prop="fulfillmentamazon" label="亚马逊预估快递费"></el-table-column>
                <el-table-column width="150px" prop="fulfillmentfeeoutsea" label="海外仓预估快递费(基础运费+附加费)"></el-table-column>
                <el-table-column width="150px" prop="productlenghcm1" label="产品尺寸CM 最长"></el-table-column>
                <el-table-column width="150px" prop="productlenghcm2" label="产品尺寸CM 次长"></el-table-column>
                <el-table-column width="150px" prop="productshortlenghcm" label="产品尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghcm1" label="外箱尺寸CM 最长"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghcm2" label="外箱尺寸CM 次长"></el-table-column>
                <el-table-column width="200px" prop="outboxshortlenghcm" label="外箱尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="weightkg1" label="整箱重量KG 净重"></el-table-column>
                <el-table-column width="200px" prop="weightkg2" label="整箱重量KG 毛重"></el-table-column>
                <el-table-column width="200px" prop="productlenghinch1" label="产品尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="productlenghinch2" label="产品尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="productshortlenghinch" label="产品尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghinch1" label="外箱尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghinch2" label="外箱尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="outboxshortlenghinch" label="外箱尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="weightlb1" label="整箱重量LB 净重"></el-table-column>
                <el-table-column width="200px" prop="weightlb2" label="整箱重量LB 毛重"></el-table-column>
                <el-table-column width="200px" prop="quantityhq" label="装柜量"></el-table-column>
                <el-table-column width="200px" prop="quantity" label="一箱几件"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghinch1" label="一箱多件内箱尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghinch2" label="一箱多件内箱尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="inboxshortlenghinch" label="一箱多件内箱尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="inboxweightlb1" label="一箱多件内箱重量LB 净重"></el-table-column>
                <el-table-column width="200px" prop="inboxweightlb2" label="一箱多件内箱重量LB 毛重"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghcm1" label="一箱多件内箱尺寸CM 最长"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghcm2" label="一箱多件内箱尺寸CM 次长"></el-table-column>
                <el-table-column width="200px" prop="inboxshortlenghcm" label="一箱多件内箱尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="inboxweightkg1" label="一箱多件内箱重量KG 净重"></el-table-column>
                <el-table-column width="200px" prop="inboxweightkg2" label="一箱多件内箱重量KG 毛重"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchheight" label="最小包装尺寸INCH 最小包装尺寸长"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchwidth" label="最小包装尺寸INCH 最小包装尺寸宽"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchhigh" label="最小包装尺寸INCH 最小包装尺寸高"></el-table-column>
                <el-table-column width="200px" prop="minweight1" label="最小包装尺寸净重"></el-table-column>
                <el-table-column width="200px" prop="minweight2" label="最小包装尺寸毛重"></el-table-column>
                <el-table-column width="200px" prop="type" label="产品类型"></el-table-column>
                <el-table-column width="200px" prop="timestamp" label="更新时间"></el-table-column>
              </el-table>
            </el-collapse>
          </el-dialog>
        </div>

        <div>
          <el-dialog  width="100%" title="产品详细信息" :visible="showMoreProductDialog" @close="closeMoreProductDialog">
            <el-collapse >
              <el-table :data="moreProductData" class="custom-table">
                <el-table-column width="200px" prop="factory" label="工厂"></el-table-column>
                <el-table-column width="200px" prop="sku" label="款号"></el-table-column>
                <el-table-column width="250px" prop="fba" label="产品属性FBA"></el-table-column>
                <el-table-column width="200px" prop="fbm" label="产品属性FBM"></el-table-column>
                <el-table-column width="150px" prop="fulfillmentamazon" label="亚马逊预估快递费"></el-table-column>
                <el-table-column width="150px" prop="fulfillmentfeeoutsea" label="海外仓预估快递费(基础运费+附加费)"></el-table-column>
                <el-table-column width="150px" prop="productlenghcm1" label="产品尺寸CM 最长"></el-table-column>
                <el-table-column width="150px" prop="productlenghcm2" label="产品尺寸CM 次长"></el-table-column>
                <el-table-column width="150px" prop="productshortlenghcm" label="产品尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghcm1" label="外箱尺寸CM 最长"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghcm2" label="外箱尺寸CM 次长"></el-table-column>
                <el-table-column width="200px" prop="outboxshortlenghcm" label="外箱尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="weightkg1" label="整箱重量KG 净重"></el-table-column>
                <el-table-column width="200px" prop="weightkg2" label="整箱重量KG 毛重"></el-table-column>
                <el-table-column width="200px" prop="productlenghinch1" label="产品尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="productlenghinch2" label="产品尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="productshortlenghinch" label="产品尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghinch1" label="外箱尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="outboxlenghinch2" label="外箱尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="outboxshortlenghinch" label="外箱尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="weightlb1" label="整箱重量LB 净重"></el-table-column>
                <el-table-column width="200px" prop="weightlb2" label="整箱重量LB 毛重"></el-table-column>
                <el-table-column width="200px" prop="quantityhq" label="装柜量"></el-table-column>
                <el-table-column width="200px" prop="quantity" label="一箱几件"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghinch1" label="一箱多件内箱尺寸INCH 最长"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghinch2" label="一箱多件内箱尺寸INCH 次长"></el-table-column>
                <el-table-column width="200px" prop="inboxshortlenghinch" label="一箱多件内箱尺寸INCH 最短"></el-table-column>
                <el-table-column width="200px" prop="inboxweightlb1" label="一箱多件内箱重量LB 净重"></el-table-column>
                <el-table-column width="200px" prop="inboxweightlb2" label="一箱多件内箱重量LB 毛重"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghcm1" label="一箱多件内箱尺寸CM 最长"></el-table-column>
                <el-table-column width="200px" prop="inboxlenghcm2" label="一箱多件内箱尺寸CM 次长"></el-table-column>
                <el-table-column width="200px" prop="inboxshortlenghcm" label="一箱多件内箱尺寸CM 最短"></el-table-column>
                <el-table-column width="200px" prop="inboxweightkg1" label="一箱多件内箱重量KG 净重"></el-table-column>
                <el-table-column width="200px" prop="inboxweightkg2" label="一箱多件内箱重量KG 毛重"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchheight" label="最小包装尺寸INCH 最小包装尺寸长"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchwidth" label="最小包装尺寸INCH 最小包装尺寸宽"></el-table-column>
                <el-table-column width="200px" prop="minlenghinchhigh" label="最小包装尺寸INCH 最小包装尺寸高"></el-table-column>
                <el-table-column width="200px" prop="minweight1" label="最小包装尺寸净重"></el-table-column>
                <el-table-column width="200px" prop="minweight2" label="最小包装尺寸毛重"></el-table-column>
                <el-table-column width="200px" prop="type" label=""></el-table-column>



              </el-table>
            </el-collapse>
          </el-dialog>
        </div>

        <div  >
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[4, 10, 40, 80]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import dragscroll from 'dragscroll';
import axios, {request} from "axios";


export default {
  name: 'HomeView' ,
  components: {
    HelloWorld
  },
  data() {
    return {
      tableData: [],
      showAllFields: true,
      productLogData:{},
      moreProductData:{},
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      pageSize:4,
      pageNum:1,
      sku:'',
      productType:'',
      showUpdateDialog: false, // 控制日志弹出窗口的显示与隐藏
      showMoreProductDialog: false,
      factory: '',

    }
  },
  mounted() {
    if (this.showUpdateDialog) {
      this.fetchProductLogs(); // 在弹出窗口加载时获取数据
    }
  },
  created: function () {
    //请求分页查询所需的数据
    this.load();
  },
  watch: {
    productType(newType) {
      this.selectProductType(newType);
    }
  },
  methods:{
    load() {
      let url =
          "http://192.168.40.243:9090/product/page?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&sku=" +
          this.sku +
          "&factory="+
          this.factory+
          "&type=" +
          encodeURIComponent(this.productType);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.moreProductData = res.data;
            this.total = res.total;
          });
    },
    handleSizeChange(pageSize) {
      //console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      //console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    },
    getUrl(row,imageFlag){
      //console.log(row.url)
        return  imageFlag===1 ? `/image/${row.url1}` : `/image/${row.url2}`;
    },
    selectProductType(type) {
      this.productType = type;
      this.pageNum = 1;
      this.load();
    },
    showUpdateHistory(id) {

      this.fetchProductLogs(id);//获取当前行的id 对应后端的时productId
      this.showUpdateDialog = true;//打开弹出窗口

    },
    showMoreProduct(id) {
      console.log(id)
      this.fetchMoreProduct(id);
      this.showMoreProductDialog = true;
    },
    closeMoreProductDialog() {
      // 关闭弹出窗口
      this.moreProductData = {};
      this.showMoreProductDialog = false;
    },
    closeUpdateDialog() {
      // 关闭弹出窗口
      this.productLogData = {};
      this.showUpdateDialog = false;
    },
    fetchProductLogs(id) {
      axios.get(`/product/api/productlogs/${id}`)
          .then(response =>{
            this.productLogData=response.data.data;
          })
          .catch(error => {
            console.error(error);
          });
    },
    toggleFields() {
      this.showAllFields = !this.showAllFields;
      console.log(this.showAllFields);
    },
    fetchMoreProduct(id) {
      let url =
          "http://192.168.40.243:9090/product/page?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&sku=" +
          this.sku +
          "&type=" +
          encodeURIComponent(this.productType);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.moreProductData = res.data;
          });
    },






  }

}
</script>
