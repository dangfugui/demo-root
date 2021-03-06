package dang.demo.sharding.shardingjdbc.module.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    @Id
    @Column(name = "config_id")
    private Long configId;
    private String name;
    private String value;
}
