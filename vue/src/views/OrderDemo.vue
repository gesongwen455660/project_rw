<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height:100%">
      <el-menu
          :default-openeds="['1']"

      >
        <el-submenu index="1" >
          <template slot="title"><i class="el-icon-message"></i>试运行</template>  <!--导航1-->
          <el-menu-item-group>
            <el-menu-item index="all" @click="productPath">全部产品</el-menu-item>
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
            <el-menu-item index="订单(demo)">订单(demo)</el-menu-item>
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
        <template>
          <div class="container">
            <div class="select-container">
              <select class="selectOption1" v-model="currentSelectId">
                <option value="all">全部店铺</option>
                <option value="srw">srw</option>
                <option value="szvc">深圳VC</option>
                <option value="wfas">WFAS</option>
                <option value="wfrw">WFRW</option>
              </select>
            </div>
            <div class="select-container">
              <select class="selectOption1" v-model="currentSelectRepository">
                <option value="all">全部仓库</option>
                <option value="仓库1(待编辑)">仓库1(待编辑)</option>
                <option value="仓库2(待编辑)">仓库2(待编辑)</option>
                <option value="仓库3(待编辑)">仓库3(待编辑)</option>
                <option value="仓库4(待编辑)">仓库4(待编辑)</option>
              </select>
            </div>
          </div>
        </template>

      </el-header>

      <el-main v-if="currentSelectId === 'all'">

        <template>
          <div class="container">
            <div class="input-container">
              <el-upload
                  action="http://192.168.40.243:9090/product/api/importExcel"
                  accept=".xls,.xlsx"
                  :on-success="importExcelHandleSuccess"
                  :on-error="importExcelHandleError"
              >
                <el-button slot="trigger" size="small" type="primary">导入 Excel 文件</el-button>
              </el-upload>
            </div>
            <div class="input-container" >
              <el-input type="text" style="width: 300px" placeholder="查询订单"  v-model="orderid" @keyup.native.enter="loadAll"></el-input><el-button type="primary" @click="loadAll">搜索</el-button>
            </div>
          </div>
        </template>

        <el-table :data="tableDataAll">

          <el-table-column prop="status" label="状态" :filters="statusFilters" :filter-method="filterStatus">
          </el-table-column>
          <el-table-column prop="orderid" label="订单编号" >
          </el-table-column>
          <el-table-column prop="flag" label="店铺" :filters="flagFilters" :filter-method="filterFlag" >
          </el-table-column>
          <el-table-column prop="date" label="日期" >
          </el-table-column>
          <el-table-column prop="sku" label="sku" >
          </el-table-column>
          <el-table-column prop="type" label="交易类型">
          </el-table-column>
          <el-table-column prop="amazonorderid" label="亚马逊订单号" >
          </el-table-column>
<!--          <el-table-column label="其他">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="">查看其他信息</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table> <!-- 主表单数据展示界面 -->
        <div>
          <el-pagination
              @size-change="handleSizeChangeAll"
              @current-change="handleCurrentChangeAll"
              :current-page="pageNum"
              :page-sizes="[60, 80, 100, 200]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div><!-- 分页设置 -->
      </el-main>

      <el-main v-else-if="currentSelectId === 'szvc'">

        <div class="select-container" >
          <el-input type="text" style="width: 300px" placeholder="查询订单"  v-model="orderid" @keyup.native.enter="loadSzvc"></el-input><el-button type="primary" @click="loadSzvc">搜索</el-button>
        </div>
        <el-table :data="tableDataSzvc">

          <el-table-column prop="" label="状态" >
          </el-table-column>
          <el-table-column prop="orderid" label="订单编号" >
          </el-table-column>
          <el-table-column prop="shippeddate" label="到达日期" >
          </el-table-column>
          <el-table-column prop="trackingid" label="跟踪号">
          </el-table-column>
          <el-table-column prop="sku" label="sku" >
          </el-table-column>
<!--          <el-table-column label="其他">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="">查看其他信息</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table> <!-- 主表单数据展示界面 -->
        <div>
          <el-pagination
              @size-change="handleSizeChangeSzvc"
              @current-change="handleCurrentChangeSzvc"
              :current-page="pageNum"
              :page-sizes="[10, 20, 40, 80]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div><!-- 分页设置 -->
      </el-main>

      <el-main v-else-if="currentSelectId === 'wfas'">

        <div class="select-container" >
          <el-input type="text" style="width: 300px" placeholder="查询订单"  v-model="ponumber" @keyup.native.enter="loadWfas"></el-input><el-button type="primary" @click="loadWfas">搜索</el-button>
        </div>
        <el-table :data="tableDataWfas">

          <el-table-column prop="status" label="状态" >
          </el-table-column>
          <el-table-column prop="ponumber" label="订单编号" >
          </el-table-column>
          <el-table-column prop="readyforpickupdate" label="到达日期" >
          </el-table-column>
          <el-table-column prop="trackingid" label="跟踪号">
          </el-table-column>
          <el-table-column prop="sku" label="sku" >
          </el-table-column>
          <el-table-column prop="warehousename" label="仓库" >
          </el-table-column>
<!--          <el-table-column label="其他">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="">查看其他信息</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table> <!-- 主表单数据展示界面 -->
        <div>
          <el-pagination
              @size-change="handleSizeChangeWfas"
              @current-change="handleCurrentChangeWfas"
              :current-page="pageNum"
              :page-sizes="[10, 20, 40, 80]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div><!-- 分页设置 -->
      </el-main>

      <el-main v-else-if="currentSelectId === 'wfrw'">

        <div class="select-container" >
          <el-input type="text" style="width: 300px" placeholder="查询订单"  v-model="ponumber" @keyup.native.enter="loadWfrw"></el-input><el-button type="primary" @click="loadWfrw">搜索</el-button>
        </div>
        <el-table :data="tableDataWfrw">

          <el-table-column prop="status" label="状态">
          </el-table-column>
          <el-table-column prop="ponumber" label="订单编号" >
          </el-table-column>
          <el-table-column prop="readyforpickupdate" label="到达日期" >
          </el-table-column>
          <el-table-column prop="trackingid" label="跟踪号">
          </el-table-column>
          <el-table-column prop="sku" label="sku" >
          </el-table-column>
          <el-table-column prop="warehousename" label="仓库" >
          </el-table-column>
<!--          <el-table-column label="其他">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button @click="">查看其他信息</el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table> <!-- 主表单数据展示界面 -->
        <div>
          <el-pagination
              @size-change="handleSizeChangeWfrw"
              @current-change="handleCurrentChangeWfrw"
              :current-page="pageNum"
              :page-sizes="[10, 20, 40, 80]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total=total>
          </el-pagination>
        </div><!-- 分页设置 -->
      </el-main>

    </el-container><!-- 右上角gsw界面 待完成 -->
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
      tableDataSzvc: [
      ],
      tableDataAll:[
      ],
      tableDataWfas:[
      ],
      tableDataWfrw:[
      ],
      tableDataSrw:[
      ],
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      pageSize:60,
      pageNum:1,
      currentSelectId:'all', //店铺下拉选择框
      currentSelectRepository:'all',//仓库下拉选择框
      orderid:'',
      wcs1:'',
      ponumber:'', //wayfair订单的订单号
      warehousename:'',
      statusFilters: [ // 状态筛选选项
        { text: '发货', value: '发货' },
        { text: '未发货', value: '未发货' }
      ],
      flagFilters:[
        { text :'srw', value: 'srw' },
        { text :'szvc', value: 'szvc' },
        { text :'wfas', value: 'wfas' },
        { text :'wfrw', value: 'wfrw' },
        { text :'zn', value: 'zn' }
      ],
    }
  },
  created: function() {
    // 根据初始选择调用对应的加载函数
        this.loadAll();

  },
  watch: {
    currentSelectId: function(newVal, oldVal) {
      switch (newVal) {
        case 'srw':
          this.loadSrw();
          break;
        case 'szvc':
          this.loadSzvc();
          break;
        case 'wfas':
          this.loadWfas();
          break;
        case 'wfrw':
          this.loadWfrw();
          break;
        default:
          this.loadAll();
          break;
      }
    }
  },
  methods: {
    loadAll() {
      let url =
          "http://192.168.40.243:9090/simpleOrder1/pageAll?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&orderid=" +
          this.orderid +
          "&wcs1=" +
          encodeURIComponent(this.warehousename);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableDataAll = res.data;
            this.total = res.total;
          });
    },
    loadSzvc() {
      let url =
          "http://192.168.40.243:9090/simpleOrder1/page?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&orderid=" +
          this.orderid +
          "&wcs1=" +
          encodeURIComponent(this.wcs1);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableDataSzvc = res.data;
            this.total = res.total;
          });
    },
    loadWfas() {
      let url =
          "http://192.168.40.243:9090/simpleOrder1/pageTwo?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&ponumber=" +
          this.ponumber +
          "&warehousename=" +
          encodeURIComponent(this.warehousename);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableDataWfas = res.data;
            this.total = res.total;
          });
    },
    loadWfrw() {
      let url =
          "http://192.168.40.243:9090/simpleOrder1/pageThree?pageNum=" +
          this.pageNum +
          "&pageSize=" +
          this.pageSize +
          "&ponumber=" +
          this.ponumber +
          "&warehousename=" +
          encodeURIComponent(this.warehousename);

      fetch(url)
          .then((res) => res.json())
          .then((res) => {
            //console.log(res);
            this.tableDataWfrw = res.data;
            this.total = res.total;
          });
    },

    handleSizeChangeSzvc(pageSize) {
      //console.log(pageSize)
      this.pageSize = pageSize
      this.loadSzvc()
    },
    handleCurrentChangeSzvc(pageNum) {
      //console.log(pageNum)
      this.pageNum = pageNum
      this.loadSzvc()
    },
    handleSizeChangeAll(pageSize) {
      //console.log(pageSize)
      this.pageSize = pageSize
      this.loadAll()
    },
    handleCurrentChangeAll(pageNum) {
      //console.log(pageNum)
      this.pageNum = pageNum
      this.loadAll()
    },
    handleSizeChangeWfas(pageSize) {
      //console.log(pageSize)
      this.pageSize = pageSize
      this.loadWfas()
    },
    handleCurrentChangeWfas(pageNum) {
      //console.log(pageNum)
      this.pageNum = pageNum
      this.loadWfas()
    },
    handleSizeChangeWfrw(pageSize) {
      //console.log(pageSize)
      this.pageSize = pageSize
      this.loadWfrw()
    },
    handleCurrentChangeWfrw(pageNum) {
      //console.log(pageNum)
      this.pageNum = pageNum
      this.loadWfrw()
    },
    productPath(){
      this.$router.push('/admin');
    },
    filterStatus(value, row) { //筛选器函数
      return row.status === value;
    },
    filterFlag(value, row) { //筛选器函数
      return row.status === value;
    },
    importExcelHandleSuccess(response, file, fileList) {
      this.$message.success('文件上传成功');
      // 如果需要，你可以在这里重新加载数据
    },
    importExcelHandleError(err, file, fileList) {
      this.$message.error('文件上传失败');
    }
      // async downloadFile(orderNumber) { 下载接口暂时关闭
      //   const url = `http://192.168.40.243:9090/download/simpleOrder/${orderNumber}`;
      //
      //   try {
      //     const response = await axios.get(url, {
      //       responseType: 'blob' // 设置响应类型为二进制数据
      //     });
      //
      //     const downloadUrl = URL.createObjectURL(response.data);
      //
      //     const link = document.createElement('a');
      //     link.href = downloadUrl;
      //     link.download = `order_${orderNumber}.pdf`;
      //
      //     document.body.appendChild(link);
      //     link.click();
      //
      //     URL.revokeObjectURL(downloadUrl);
      //     document.body.removeChild(link);
      //   } catch (error) {
      //     console.error('下载文件时发生错误:', error);
      //   }
      // }

  }

}
</script>
