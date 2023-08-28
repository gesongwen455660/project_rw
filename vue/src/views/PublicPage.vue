<template>
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
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style=" font-size: 12px">
        <el-dropdown style="float: right">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
          <span>gsw</span>
        </el-dropdown>

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
          </div>
        </template>

        <el-table :data="tableData">

          <el-table-column label="图片" prop="url">
            <template slot-scope="scope">
              <el-popover placement="top-start" title="" trigger="hover">
                <img :src=getUrl(scope.row) alt="" style="width: 800px;height: 800px"> //getUrl(scope.row) getUrl(scope.row)
                <img slot="reference" :src=getUrl(scope.row) style="width: 150px;height: 150px">
              </el-popover>
            </template>
          </el-table-column>



          <el-table-column prop="sku" label="SKU" width="140">
          </el-table-column>
          <el-table-column prop="sku13" label="13位SKU编码" width="130">
          </el-table-column>
          <el-table-column prop="chicun1" label="产品尺寸">
          </el-table-column>
          <el-table-column prop="chicun2" label="包装尺寸">
          </el-table-column>
          <el-table-column prop="color" label="颜色">
          </el-table-column>
          <el-table-column prop="weight1" label="净重">
          </el-table-column>
          <el-table-column prop="weight2" label="毛重">
          </el-table-column>
          <el-table-column prop="input" label="输入功率">
          </el-table-column>
          <el-table-column prop="quantityHq" label="装柜量(40HQ)">
          </el-table-column>
          <el-table-column label="最后更新时间">
            <template slot-scope="scope">
              <el-button @click="showUpdateHistory(scope.row.id)">查看更新日志</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div>
          <el-dialog  width="100%" title="更新日志" :visible="showUpdateDialog" @close="closeUpdateDialog">
            <el-collapse >
              <el-table :data="productLogData">
                <el-table-column width="200px" prop="sku13" label="sku"></el-table-column>
                <el-table-column width="200px" prop="sku13" label="sku13"></el-table-column>
                <el-table-column width="250px" prop="chicun1" label="产品尺寸"></el-table-column>
                <el-table-column width="200px" prop="chicun2" label="包装尺寸"></el-table-column>
                <el-table-column width="150px" prop="color" label="颜色"></el-table-column>
                <el-table-column width="150px" prop="weight1" label="净重"></el-table-column>
                <el-table-column width="150px" prop="weight2" label="毛重"></el-table-column>
                <el-table-column width="150px" prop="input" label="输入功率"></el-table-column>
                <el-table-column width="150px" prop="quantityHQ" label="装柜量(40HQ)"></el-table-column>
                <el-table-column width="200px" prop="timestamp" label="更新时间"></el-table-column>
              </el-table>
            </el-collapse>
          </el-dialog>
        </div>

        <div  >
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[10, 20, 40, 80]"
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
import axios, {request} from "axios";


export default {
  name: 'HomeView' ,
  components: {
    HelloWorld
  },
  data() {
    return {
      tableData: [],
      productLogData:{},
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      pageSize:10,
      pageNum:1,
      sku:'',
      productType:'',
      showUpdateDialog: false, // 控制日志弹出窗口的显示与隐藏


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
          "&type=" +
          encodeURIComponent(this.productType);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableData = res.data;
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
    getUrl(row){
      //console.log(row.url)
      return `/image/${row.url}`;
    },
    selectProductType(type) {
      this.productType = type;
      this.pageNum = 1;
      this.load();
    },
    showUpdateHistory(id) {
      console.log(id)
      this.fetchProductLogs(id);//获取当前行的id 对应后端的时productId
      this.showUpdateDialog = true;//打开弹出窗口

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
    }





  }

}
</script>
