package cn.coderjia.netty.protobuf.demo1;

/**
 * @Author CoderJiA
 * @Description TestPB
 * @Date 5/2/19 下午7:05
 **/
public class TestPB {

    public static void main(String[] args) throws Exception {

        DataInfo.Student sdt = DataInfo.Student.newBuilder()
                .setName("cj")
                .setAge(24)
                .setAddress("ly")
                .build();

        byte[] sdtBytes = sdt.toByteArray();

        DataInfo.Student sdtR = DataInfo.Student.parseFrom(sdtBytes);

        System.out.println("name:" + sdtR.getName() + "\n" + "age:" + sdtR.getAge() + "\n" + "address:" + sdtR.getAddress());

    }
}
