package co.chimeralabs.advertiser.server.repository;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.chimeralabs.advertiser.server.model.TextureImageFormat;

@Repository
@Transactional
public interface TextureImageFormatRepository extends JpaRepository<TextureImageFormat, Serializable>{

}
