import random
import pygame
pygame.init()

SCREEN_RECT = pygame.Rect(0, 0, 480, 650)
FRAME_PER_SEC = 60  # 游戏帧率
# 创建敌机定时器常量
CREATE_ENEMY_EVENT = pygame.USEREVENT
HERO_FIRE_EVENT = pygame.USEREVENT + 1  # 发射子弹


class GameSprite(pygame.sprite.Sprite):
    """ 飞机大战精灵 """

    def __init__(self, image_name, speed=1):
        # 调用父类方法
        super().__init__()

        # 调用对象属性
        self.image = pygame.image.load(image_name)
        self.rect = self.image.get_rect()
        self.speed = speed

    def update(self):
        # 屏幕垂直方向移动
        self.rect.y += self.speed


class BackGround(GameSprite):
    '''游戏背景精灵'''

    def __init__(self, is_alt=False):
        # 1调用父类完成精灵创建
        super().__init__('./images/background.png')
        # 2判断是否是交替图像 如果是 需要设置初始位置
        if is_alt:
            self.rect.y = -self.rect.height

    def update(self):
        # 调用父类方法
        super().update()

        # 判断是否移动出屏幕
        if self.rect.y >= SCREEN_RECT.height:
            self.rect.y = -self.rect.height
        pass


class Enemy(GameSprite):
    """敌机精灵"""

    def __init__(self):
        # 1调用父类方法 创建敌机精灵 同时指定敌机图片
        super().__init__("./images/enemy1.png")
        # 2 指定敌机速度 1-3
        self.speed = random.randint(1, 3)
        # 3 指定敌机随机位置
        self.rect.bottom = 0
        max_x = SCREEN_RECT.width - self.rect.width
        self.rect.x = random.randint(0, max_x)

    def update(self):
        # 1调用父类方法 保持垂直飞行
        super().update()
        # 2 判断是否飞出屏幕 飞出则删除
        if self.rect.y >= SCREEN_RECT.height:
            # print("飞出屏幕 删除")
            # kill可以将精灵移除精灵库 kill方法调用时del方法自己会被调用
            self.kill()
        # 销毁对象

    def __del__(self):
        # print("没了")
        pass


class Hero(GameSprite):
    """英雄精灵"""

    def __init__(self):
        # 1调用父类方法 设置image和speed
        super().__init__("./images/me1.png", 0)

        # 2设置hero初始值  英雄的位置
        self.rect.centerx = SCREEN_RECT.centerx
        self.rect.bottom = SCREEN_RECT.bottom - 50
        # 3 创建子弹精灵组
        self.bullets = pygame.sprite.Group()

    # 水平移动
    def update(self):
        self.rect.x += self.speed
        # 不能离开屏幕
        if self.rect.x < 0:
            self.rect.x = 0
        elif self.rect.right > SCREEN_RECT.right:
            self.rect.right = SCREEN_RECT.right

    def fire(self):
        #print("发射子弹")
        for i in(0,1,2):
            # 创建子弹精灵
            bullet = Bullet()
            # 设置位置
            bullet.rect.bottom = self.rect.y - i*20
            bullet.rect.centerx=self.rect.centerx
            # 将精灵添加到精灵组
            self.bullets.add(bullet)


class Bullet(GameSprite):
    """子弹精灵"""

    def __init__(self):
        # 调用父类方法 设置图片速度
        super().__init__("./images/bullet1.png", -2)

    def update(self):
        # 调用父类方法 让子弹沿着垂直方向飞行
        super().update()

        # 判断是否飞出屏幕
        if self.rect.bottom < 0:
            self.kill()

    def __del__(self):
        #print("子弹销毁")
        pass
