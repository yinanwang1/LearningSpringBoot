package com.qiji.dto.common.bike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;

/**
 * @author <a href="mailto:panqing@qeek.cc">panq</a>
 * @version 2021年3月24日
 * @since
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeFeature implements Serializable {

    private static final long serialVersionUID = -7983521742044592156L;

    /**
     * 头盔锁
     */
    private Boolean helmetLock;

    /**
     * 90度停车
     */
    private Boolean verticalParking;

    /**
     * 90度停车失败次数
     */
    private Integer verticalParkingFailedNum;

    /**
     * 90度停车 开启所有停车带
     */
    private Boolean verticalParkingAllOn;

    /**
     * 是否强制戴头盔锁
     */
    @Deprecated
    private Boolean forceWearHelmet;

    /**
     * 地牌自动还车
     */
    private Boolean landCardAuto;

    /**
     * 0-未选择, 1-蓝牙地丁, 2-RFID, 3地牌, 4-拍照还车, 5-车载摄像头
     * 对应于erp->运营城市管理->车辆功能设置->地面辅助停车设施 下拉框
     */
    public Integer type;
    /**
     * 是否开启全部还车点
     */
    private Boolean openAllParkingZone;

    /**
     * v3.22版本将不在地牌允许付费还车改为还车点内调度费
     */
    private Boolean allowPayLockbike;

    /**
     * 检测失败次数, 超过该次数允许支付还车点内调度费还车
     */
    private Integer checkFailedNum;

    /**
     * 地牌容差
     */
    private Integer landCardAllowance;

    /**
     * 无地牌自动还车
     */
    private Boolean noLandcardLockbike;

    /**
     * 强制取头盔、强制佩戴  0-关闭 1-强制取头盔 2-强制佩戴 3-半强制佩戴
     */
    private Integer wearHelmet;

    /**
     * 载人感应  0-关闭 1-开启
     */
    private Integer mannedInduction;

    /**
     * 载重阈值, 公斤
     */
    private Integer weightThreshold;

    /**
     * 头盔不在位车辆处理
     */
    private Byte helmetNotPosHandleType;

    /**
     * 还车点容差
     */
    private Integer parkingZoneTolerance;

    /**
     * 还车点外还车
     */
    private Boolean outsideParkingZoneLock;

    /**
     * 开启rfid配置
     */
    private Boolean openRfid;

    /**
     * rfid 过期秒数
     */
    private Integer rfidOverdueSecond;

    /**
     * 头盔低电量处理
     * HelmetNotPosHandleTypeEnum
     */
    private Byte helmPowerHandle;

    /**
     * 低电量阈值
     * HelmLowPowerPercentEnum
     */
    private Integer helmPowerPercent;

    /**
     * 拍照还车失败次数: 失败N次之后允许跳过校验, 0-99
     */
    private Integer photoParkFailedNum;

    /**
     * 拍照还车车辆停放角度阈值: 0-90
     */
    private Integer photoParkAngle;

    /**
     * 拍照还车停放距离阈值: 0-99cm
     */
    private Integer photoParkDistance;

    /**
     * 是否开启还车申请；true开启，false关闭；默认false
     */
    private Boolean allowApplyPark;

    /**
     * 多少天内自动通过；范围1~9999，默认值为7
     */
    private Integer applyParkDays;

    /**
     * 免审次数；范围1~99，默认值为1
     */
    private Integer applyParkNum;

    /**
     * 头盔角度；范围0~180，默认值为空
     */
    private Integer helmetAngle;

    /**
     * 头盔检测时间: 默认 15s
     */
    private Integer helmetDetectionTime;

    /**
     * 车载摄像头还车角度: 默认值为-1
     */
    private Integer bikeCameraAngle;

    /**
     * 是否开启还车点扩大容差
     */
    private Boolean parkingZoneExpandScope;

    /**
     * 还车点扩大容差1: 第一次扩大容差距离
     */
    private Integer parkingZoneExpandScopeTolerance1;

    /**
     * 还车点扩大容差2: 第二次扩大容差距离
     */
    private Integer parkingZoneExpandScopeTolerance2;

    public Boolean getValue() {
        return openAllParkingZone;
    }

    public Boolean getVerticalParkingAllOn() {
        Boolean verticalParkingAllOn0 = verticalParkingAllOn == null ? verticalParking : verticalParkingAllOn;
        return ObjectUtils.defaultIfNull(verticalParkingAllOn0, false);
    }

}
